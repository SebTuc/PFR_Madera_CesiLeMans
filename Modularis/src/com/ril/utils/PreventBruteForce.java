package com.ril.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The preventBruteForce, used for prevent attack brute force with different session (attack with vpn and other session/ adresse IP etc...) 
 * and the same session. \n
 * @author TUCCIO Sébastien
 * @version 0.0.1
 * @since java 1.8 or over
 */
public class PreventBruteForce {

	

	//number of attempts for one session
	private static final int NUMBER_ATTEMPTS_SESSION = 3;
	//number max of attemps for all session in application to try a login 
	private static final int NUMBER_ATTEMPTS_APPLICATION = 5;
	
	public static int numberOfLockSessionLogin = 0;
	public static int numberOfLockApplicationLogin = 0;
	
	
	private static List<AttemptsTry> ALL_TENTATIVE = new ArrayList<AttemptsTry>();
	private static List<TimeRemaining> ALL_LOGIN_WAITING = new ArrayList<TimeRemaining>();

	/** 
	 *@param login  login try by the user.
	 *@param sessionId   HTTPSession ID of the user who is trying to connect.
	 *
	 * @return
	 * return True if brute force is detected (until the waiting time is over) and False if nothing was detected.
	 * 
	 */
	public static boolean DetectBruteForce(String login , String sessionId) {

		boolean flag = false;
		/*
		 *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * FAIRE LE CYCLE DE VIE DES BEAN (LES LIST ALL_LOGIN_WAITING | ALL_TENTATIVE )
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
		//Get the timeRemaining to know if brute force is active for login and session
		if(!ifSessionLockThisLogin(login,sessionId)){
			//Init only if the timeRemaining is over
			initAttemptsTry(login,sessionId);
			//count attemps detect for this session with same login
			int countForSession = countAllTentativeForTheSession(login,sessionId);
			if(countForSession >= NUMBER_ATTEMPTS_SESSION) {
				//Sorry... don't try again :D
				initTimeRemaining(login,sessionId);
				numberOfLockSessionLogin++;
				flag = true;
			}

			//count attemps detect for same login in total of ALL_TENTATIVE
			int countForApplication = countAllTentativeForTheApplication(login);
			if(countForApplication >= NUMBER_ATTEMPTS_APPLICATION) {
				//Sorry... don't try again :D
				initTimeRemaining(login,"ALL");
				numberOfLockApplicationLogin++;
				flag = true;

			}
		}else {
			
			flag = true;
		}
		


		return flag;
	}

	/*
	 * Check if Login is locked
	 */
	
	private static boolean ifSessionLockThisLogin(String login , String sessionId) {
		boolean flag = false;
		
		Long timeNow = System.currentTimeMillis();
		int countLoginSessionLock  =  ALL_LOGIN_WAITING.stream().filter(s -> s.getLoginWait().equals(login) && s.getTimeWait() >= timeNow && (s.getSessionId().equals(sessionId) || s.getSessionId().equals("ALL"))).collect(Collectors.toList()).size();
		if(countLoginSessionLock != 0) {
			flag = true;
		}
		
		return flag;
		
	}
	
	
	/*
	 * Count attempts
	 */
	
	private static int countAllTentativeForTheSession(String login , String sessionId) {
		
		return ALL_TENTATIVE.stream().filter(s -> s.getLogin().equals(login) && s.getSessionId().equals(sessionId)).collect(Collectors.toList()).size();
	}
	
	private static int countAllTentativeForTheApplication(String login) {
		
		return ALL_TENTATIVE.stream().filter(s -> s.getLogin().equals(login)).collect(Collectors.toList()).size();
	}
	
	
	/*
	 * Initialize
	 */
	
	private static void initTimeRemaining(String login,String sessionId) {
		
		TimeRemaining timeRemaining = new TimeRemaining(login,sessionId);
		
		ALL_LOGIN_WAITING.add(timeRemaining);
		
	}
	
	private static void initAttemptsTry(String login , String sessionId) {

		Long timeAccess = System.currentTimeMillis();
		//Init new tentative
		AttemptsTry attempts = new AttemptsTry(login,sessionId,timeAccess);

		ALL_TENTATIVE.add(attempts);

	}
	

}

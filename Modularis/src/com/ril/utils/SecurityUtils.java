package com.ril.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The SecurityUtils , is a java library that allows you to secure an application easily and efficiently
 * @author TUCCIO Sébastien
 * @version 0.0.1
 * @since java 1.8 or over
 */

public class SecurityUtils {

	private static final int TIME_LIFE_FOR_ATTEMPTS = ConstanteSecurity.getTimeLifeForAttempts();
	private static final int NUMBER_ATTEMPTS_SESSION = ConstanteSecurity.getNumberAttemptsSession();
	private static final int NUMBER_ATTEMPTS_APPLICATION = ConstanteSecurity.getNumberAttemptsApplication();
	
	private static List<LoginAttempts> ALL_ATTEMPTS = new ArrayList<LoginAttempts>();
	private static List<TimeRemaining> ALL_LOGIN_WAITING = new ArrayList<TimeRemaining>();
	
	/** 
	 * <p>This function allows to store (for a short period) and analyze the<br>
	 * application flows in order to prevent intrusion attempts by multiple requests (Brute force)<br>
	 * <b>This function is used before the basic check of the login password and login.</b></p>
	 *
	 * @param   
	 *
	 * @return
	 * return <b>True</b> if Multi request is detected (until the waiting time is over) and <b>False</b> if nothing was detected.
	 * 
	 * @author TUCCIO Sébastien
	 * 
	 */
	public static boolean PreventMultiLoginRequest(String login , String sessionId) {

		boolean flag = false;
		
		clearAttemptsAndTimeRemaining();
		
		if(!ifSessionLockThisLogin(login,sessionId)){
			//Init only if the timeRemaining is over
			initAttemptsTry(login,sessionId);
			//count attemps detect for this session with same login
			int countForSession = countAllTentativeForTheSession(login,sessionId);
			if(countForSession >= NUMBER_ATTEMPTS_SESSION) {
				initTimeRemaining(login,sessionId);
				flag = true;
			}

			//count attemps detect for same login in total of ALL_ATTEMPTS
			int countForApplication = countAllTentativeForTheApplication(login);
			if(countForApplication >= NUMBER_ATTEMPTS_APPLICATION) {
				initTimeRemaining(login,"ALL");
				flag = true;

			}
			
		}else {
			
			flag = true;
		}

		return flag;
	}
	
	/** 
	 * <p>This function is to be used after a successful connection, it will delete the information stored for login and this session ID.<br>
	 * <b>This function is not to be used for anything else!</b></p>
	 *
	 *@param 
	 *
	 * @author TUCCIO Sébastien
	 * 
	 */
	public static void SuccefullLogined(String login , String sessionId) {
		// a voir si on supprime aussi les ALL...
		List<LoginAttempts> operatedList = new ArrayList<LoginAttempts>();
		ALL_ATTEMPTS.stream()
		.filter(s -> s.getLogin().equals(login) && s.getSessionId().equals(sessionId))
		.forEach(s -> {
			operatedList.add(s);
		});
	
	ALL_ATTEMPTS.removeAll(operatedList);
		
		
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
	 *  Clear
	 */
	
	private static void clearAttemptsAndTimeRemaining() {
		
		clearAttempts();
		clearTimeRemaining();
		
	}
	
	private static void clearAttempts() {
		// 1 minute = 60000 milliseconde
		Long timeBeforeDelete = System.currentTimeMillis() - (TIME_LIFE_FOR_ATTEMPTS* 60000);
		List<LoginAttempts> operatedList = new ArrayList<LoginAttempts>();
		
		ALL_ATTEMPTS.stream()
			.filter(s -> s.getTimeAccess() <= timeBeforeDelete)
			.forEach(s -> {
				operatedList.add(s);
			});
		
		ALL_ATTEMPTS.removeAll(operatedList);
		
	}
	
	//Just delete if time wait is behind now
	private static void clearTimeRemaining() {
		
		Long timeBeforeDelete = System.currentTimeMillis();
		List<TimeRemaining> operatedList = new ArrayList<TimeRemaining>();
		
		ALL_LOGIN_WAITING.stream()
		.filter(s -> s.getTimeWait() <= timeBeforeDelete)
		.forEach(s -> {
			operatedList.add(s);
		});
	
		ALL_LOGIN_WAITING.removeAll(operatedList);
	}
	
	/*
	 * Count attempts
	 */
	
	private static int countAllTentativeForTheSession(String login , String sessionId) {
		
		return ALL_ATTEMPTS.stream().filter(s -> s.getLogin().equals(login) && s.getSessionId().equals(sessionId)).collect(Collectors.toList()).size();
	}
	
	private static int countAllTentativeForTheApplication(String login) {
		
		return ALL_ATTEMPTS.stream().filter(s -> s.getLogin().equals(login)).collect(Collectors.toList()).size();
	}
	
	
	/*
	 * Initialize
	 */
	
	private static void initTimeRemaining(String login,String sessionId) {
		
		TimeRemaining timeRemaining = new TimeRemaining(login,sessionId);
		
		ALL_LOGIN_WAITING.add(timeRemaining);
		
	}
	
	private static void initAttemptsTry(String login , String sessionId) {

		//Init new attempts
		LoginAttempts attempts = new LoginAttempts(login,sessionId);

		ALL_ATTEMPTS.add(attempts);

	}
	
	
}

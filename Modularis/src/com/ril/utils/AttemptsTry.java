package com.ril.utils;

public class AttemptsTry {
	
	/*
	 * @author TUCCIO Sebastien
	 * @version 0.0.1
	 * Bean of attempts 
	 */
	
	private String login;
	private String sessionId;
	private Long timeAccess;

	protected AttemptsTry(String login , String sessionId, Long timeAccess) {

		this.login = login;
		this.sessionId = sessionId;
		this.timeAccess = timeAccess;
		
	}
	
	//Getter and Setter
	protected String getLogin() {
		return login;
	}

	protected void setLogin(String login) {
		this.login = login;
	}

	protected String getSessionId() {
		return sessionId;
	}

	protected void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	protected Long getTimeAccess() {
		return timeAccess;
	}

	protected void setTimeAccess(Long timeAccess) {
		this.timeAccess = timeAccess;
	}
	
	
}

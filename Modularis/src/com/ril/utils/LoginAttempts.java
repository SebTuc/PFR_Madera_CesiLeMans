package com.ril.utils;


/**
 * Bean of login attempts
 * @author TUCCIO Sebastien
 *  
 */

class LoginAttempts {
	
	private String login;
	private String sessionId;
	private Long timeAccess;
	
	protected LoginAttempts(String login , String sessionId) {

		this.login = login;
		this.sessionId = sessionId;
		this.timeAccess = System.currentTimeMillis();
		
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

package com.ril.utils;


/**
 * Bean of the timeRemaining, is the time to wait after multiple attemps to connect to one login
 * @author TUCCIO Sebastien
 */
class TimeRemaining {
	
	private static final int MAX_WAITING_TIME = ConstanteSecurity.getMaxWaitingTime();
	private static final int MIN_WAITING_TIME = ConstanteSecurity.getMaxWaitingTime();
	
	private Long timeWait;
	private String loginWait ;
	private String sessionId ;
	
	protected TimeRemaining(String loginWait,String sessionId) {
		
		this.timeWait = setTimeRandomly();
		this.loginWait = loginWait;
		this.sessionId = sessionId;
	}


	private Long setTimeRandomly() {
		
		Double randomNumber = Math.random() * MAX_WAITING_TIME;
		while(randomNumber < MIN_WAITING_TIME && randomNumber > MAX_WAITING_TIME ) {
			if(randomNumber > MAX_WAITING_TIME) {
				
				randomNumber+=(Math.random()*MIN_WAITING_TIME);
				
			}else {
				
				randomNumber-=(Math.random()*MIN_WAITING_TIME);
				
			}
		}
		// 1 Minute = 60 000 miliseconde
		return (System.currentTimeMillis() + (Math.round(randomNumber) * 60000));
	}
	
	/*
	 * Getter
	 */
	protected Long getTimeWait() {
		return timeWait;
	}

	protected String getLoginWait() {
		return loginWait;
	}

	protected String getSessionId() {
		return sessionId;
	}
	
}

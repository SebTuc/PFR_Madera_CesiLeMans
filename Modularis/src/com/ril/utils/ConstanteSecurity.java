package com.ril.utils;

/**
 * Constant of Security
 * @author TUCCIO Sébastien
 */

class ConstanteSecurity {
	/*
	 * In minute
	 */
	private static final int MAX_WAITING_TIME = 10;
	private static final int MIN_WAITING_TIME = 5;
	private static final int TIME_LIFE_FOR_ATTEMPTS = 12;
	
	private static final int NUMBER_ATTEMPTS_SESSION = 3;
	private static final int NUMBER_ATTEMPTS_APPLICATION = 5;
	
	/*
	 * Getter
	 */
	public static int getMaxWaitingTime() {
		return MAX_WAITING_TIME;
	}
	public static int getMinWaitingTime() {
		return MIN_WAITING_TIME;
	}
	public static int getTimeLifeForAttempts() {
		return TIME_LIFE_FOR_ATTEMPTS;
	}
	public static int getNumberAttemptsSession() {
		return NUMBER_ATTEMPTS_SESSION;
	}
	public static int getNumberAttemptsApplication() {
		return NUMBER_ATTEMPTS_APPLICATION;
	}
	
}

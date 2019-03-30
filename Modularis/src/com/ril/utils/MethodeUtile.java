package com.ril.utils;

public class MethodeUtile {
	
	public static boolean isFloat(String s) {
	    try { 
	        Float.parseFloat(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	private boolean findContains(String value, String valeur2) {

		//A améliorer pour trouver les accent également
		String valueUpper = value.toUpperCase();
		String Value2Upper = valeur2.toUpperCase();

		if(Value2Upper.contains(valueUpper)) {

			return true;

		}else {

			return false;

		}

	}

}

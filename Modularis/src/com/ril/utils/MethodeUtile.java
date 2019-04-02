package com.ril.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	public static boolean findContains(String value, String valeur2) {

		//A améliorer pour trouver les accent également
		String valueUpper = value.toUpperCase();
		String Value2Upper = valeur2.toUpperCase();

		if(Value2Upper.contains(valueUpper)) {

			return true;

		}else {

			return false;

		}

	}
	
	public static boolean isConnected(HttpServletResponse response , HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("SessionUtilisateur") == null) {
			return false;
		}else {
			return true;
		}
	}

}

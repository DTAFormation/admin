package com.dta.beans;

public class Util {

    private Util() {
    }
    
	public static String getFullUserType(String userType) {
		switch (userType) {
		case "a":
			return "Administrateur";
		case "c":
			return "Client";
		case "m":
			return "Moderateur";
		default:
			return userType;
		}
	}
	
	public static String getFullUserCivilite(String userCivility) {
		switch(userCivility) {
		case "mlle" :
			return "Mademoiselle";
		case "mme" :
			return "Madame";
		case "m" :
			return "Monsieur";
		default :
			return userCivility;
		}
	}
}

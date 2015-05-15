package com.dta.beans;

public class Util {

    private Util() {
    }
    
	public static String getFullUserType(String userType) {
		String fullType;
		switch (userType) {
		case "a":
			fullType = "administrateur";
			break;
		case "c":
			fullType = "client";
			break;
		case "m":
			fullType = "moderateur";
			break;

		default:
			fullType = userType;
			break;
		}

		return fullType;
	}
}

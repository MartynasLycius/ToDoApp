package com.backend.boiler.plate.Utils;

public class Constants {
	
private static Constants instance = null;
	
	private Constants() {
		
	}
	
	public static Constants getInstance() {
		if (instance == null) {
			instance = new Constants();
		}
		return instance;
	}

	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
	//USERN API MAP KEYS
	public static String USERS_API_MAP_KEY = "users";
}

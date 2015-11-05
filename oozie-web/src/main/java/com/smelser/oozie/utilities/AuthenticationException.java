package com.smelser.oozie.utilities;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -1888685403363341143L;

	public String getMessage(){
		return "You have supplied incorrect authentication";
	}
}

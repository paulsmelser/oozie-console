package com.smelser.oozie.utilities;

public class EncryptionException extends Exception{

	private static final long serialVersionUID = 5223483646110268962L;
	
	private Throwable innerException;
	
	public EncryptionException(Throwable inner){
		setInnerException(inner);
	}

	public Throwable getInnerException() {
		return innerException;
	}

	public void setInnerException(Throwable innerException) {
		this.innerException = innerException;
	}

}

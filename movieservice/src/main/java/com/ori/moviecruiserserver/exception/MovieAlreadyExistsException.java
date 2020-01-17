package com.ori.moviecruiserserver.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {
	
	private String message;

	public MovieAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieAlreadyExistsException [message=" + message + "]";
	}
	
	

}

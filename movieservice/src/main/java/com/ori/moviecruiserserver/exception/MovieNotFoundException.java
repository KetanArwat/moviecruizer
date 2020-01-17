package com.ori.moviecruiserserver.exception;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception{
	
	private String message;

	public MovieNotFoundException(final String message) {
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
		return "MovieNotFoundExcepion [message=" + message + "]";
	}
	
	

}

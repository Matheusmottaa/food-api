package com.food.api.domain.exception;

public class ResourceInUseException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceInUseException(String message) { 
		super(message); 
	}

	public ResourceInUseException(String message, Throwable e) { 
		super(message, e); 
	}
	
}

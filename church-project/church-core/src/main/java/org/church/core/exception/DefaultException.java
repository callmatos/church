package org.church.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DefaultException extends RuntimeException{

	/**
	 * Default Exception.
	 */
	private static final long serialVersionUID = 1L;

	// The error code.
	private Integer errorCode;
	
	// The message.
	private String message;
	
	/**
	 * Default Constructor pass only the message.
	 * 
	 * @param msg - Possible message
	 */
	public DefaultException(String msg) {
		super(msg);
	}
	
	/**
	 * Construct with all parameter to create the Exception.
	 * 
	 * @param errorCode - Error code 
	 * @param message - Message reference the error
	 * @param exception - Type of Exception.
	 */
	public DefaultException(Integer errorCode, String message, Exception exception){
		super(message, exception);
		this.errorCode = errorCode;
		this.message = message;
	}
	
}

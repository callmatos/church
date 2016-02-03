package org.church.core.exception.common;

import org.church.core.constants.EnegerErrorCode;
import org.church.core.exception.DefaultException;

/**
 * The class ErrorHandler.
 * 
 * @author charles.ma
 *
 */
public class ErrorHandler {

	
	/**
	 * Process exception. To differentiate errors based on the exception types
	 * 
	 * @param exception
	 *            the exception
	 * @return the platform exception
	 */
	public static DefaultException processExcpetion(Exception exception){

		if(exception instanceof NullPointerException){
			return new DefaultException(EnegerErrorCode.NULL_POINTER_EXCEPTION, exception.getMessage(), exception);
		}else{
			return new DefaultException(EnegerErrorCode.GENERIC_EXCEPTION, exception.getMessage(), exception);
		}
	}
	
}

package org.church.ws.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * This class encapsulates event publication functionality.
 * 
 * Notify all matching listeners registered with this application of an application event.
 *  
 * @author charles.ma
 *
 */
public class SingleResourceRetrievedEvent extends ApplicationEvent {

	// Object HttpServeltResponse.
	private final HttpServletResponse response;

	private static final long serialVersionUID = -943245279204940906L;
	
	/**
	 * Construct of Event listener
	 *  
	 * @param source - Represent the object here event list launched.
	 * @param response - Represent the object response.
	 * 
	 */
	public SingleResourceRetrievedEvent(final Object source, final HttpServletResponse response) {
		super(source);
		this.response = response;
	}

	// API
	public HttpServletResponse getResponse(){
		return this.response;
	}

}

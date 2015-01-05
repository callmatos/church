package org.church.ws.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrievedEvent extends ApplicationEvent {

	private final HttpServletResponse response;

	private static final long serialVersionUID = -943245279204940906L;
	
	public SingleResourceRetrievedEvent(final Object source, final HttpServletResponse response) {
		super(source);
		
		this.response = response;
	}

	// API
	public HttpServletResponse getResponse(){
		return this.response;
	}

}

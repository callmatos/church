package org.church.ws.event;

import javax.servlet.http.HttpServletResponse;

import org.church.core.dao.GenericDaoObject;
import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent<DOMAIN extends GenericDaoObject> extends ApplicationEvent {

	private static final long serialVersionUID = 4306334856533895556L;
	
	private final HttpServletResponse response;
	private final DOMAIN object;
	
	public ResourceCreatedEvent(final Object source, final DOMAIN objecDomain, HttpServletResponse response) {
		super(source);
		
		this.response = response;
		this.object = objecDomain;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public DOMAIN getObject() {
		return object;
	}

}

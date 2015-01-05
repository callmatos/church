package org.church.ws.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * This class represent the base method for all Resource in WS.
 * All WS have extends to it.
 * 
 * @author charles.ma
 *
 */
public class CommonResource {

	final private Logger logger = LoggerFactory.getLogger(CommonResource.class);
	
	@Autowired
	protected HttpServletRequest request;
	
	 @Autowired
	 public ApplicationEventPublisher eventPublisher;
	
}

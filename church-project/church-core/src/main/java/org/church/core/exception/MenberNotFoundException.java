package org.church.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MenberNotFoundException extends DefaultException implements
		HandlerExceptionResolver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenberNotFoundException(String msg) {
		super(msg);
	}

	public ModelAndView resolveException(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj, Exception exception) {
		
		return new ModelAndView(".MenberNotFoundException");
	}

}

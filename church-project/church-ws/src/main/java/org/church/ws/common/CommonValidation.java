package org.church.ws.common;

import org.springframework.validation.Validator;

public abstract class CommonValidation<DOMAIN> implements Validator,CrudValidator<DOMAIN> {

	//Class referent the domain.
	private Class domainD;
	
	// All childrem have call this constructor.
	/**
	 * This constructor to inject the type of Class domain.
	 * 
	 * Example: if DOMAIN is Member, the constructor pass the Member.class.
	 * 
	 * @param domain
	 */
	public CommonValidation(Class domain){
		this.domainD = domain;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return domainD.equals(arg0);
	}

	protected boolean isObjectsNull(Object... targets) {
		 for(Object target : targets)
			 if(isNull(target))
				 return true;

		 return false;
	}

	protected boolean isNull(Object target) {
		if(target == null)
			return true;
		else
			return false;
	}

}

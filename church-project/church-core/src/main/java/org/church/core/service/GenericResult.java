package org.church.core.service;

import java.util.List;

/**
 * This interface represent the result.
 * 
 * @author charles.ma
 *
 * @param <DOMAIN> The class than represent the Entity.
 */
public interface GenericResult<DOMAIN> {

	public abstract void setResult(DOMAIN entity);
	public abstract void setResult(List<DOMAIN> list);
	public abstract DOMAIN getTarget();
	
	
}

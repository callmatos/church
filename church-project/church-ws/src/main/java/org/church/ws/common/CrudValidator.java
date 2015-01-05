package org.church.ws.common;

import org.springframework.validation.Errors;

/**
 * This interface represent the method crud of validation.
 * 
 * @author charles.ma
 *
 */
public interface CrudValidator<DOMAIN> {

	/**
	 * Method to verify when some createdy is correctly.
	 * 
	 * @param d - Object of DOMAIN( e.g: Member, Word etc.
	 * @return
	 */
	public boolean isCreateValidator(DOMAIN d,Errors errors);
	
	/**
	 * Verify if the correctly update.
	 * 
	 * @param d
	 * @return
	 */
	public boolean isUpdateValidator(DOMAIN d,Errors errors);
	
	/**
	 * Verify to the correctly delete.
	 * @param d
	 * @return
	 */
	public boolean isDeleteValidator(DOMAIN d,Errors errors);
	
	
	
}

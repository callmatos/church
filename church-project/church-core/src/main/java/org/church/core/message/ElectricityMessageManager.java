package org.church.core.message;

/**
 * 	This Interface has the methods necessary to integration with ACTIVEMQ.
 * 	
 * @author charles.ma
 *
 */
public interface ElectricityMessageManager {

	/**
	 * This method is used to send Message to ACTIVEMQ.
	 * 
	 * @param information
	 */
	public void sendMessage(ACTIVEMQ type, String ... information);
	
}

package com.church.model.message.component;

import java.sql.Timestamp;

import org.church.core.exception.common.ErrorHandler;
import org.church.core.message.ACTIVEMQ;
import org.church.core.message.ElectricityMessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.church.model.entity.message.ElectricityMessage;

/**
 * This class implement the interface ElectricityMessageManager responsible by method to integration to ACTIVEMQ.
 * 
 *  
 * @author charles.ma
 *
 */
@Component
public class ElectricityMessageManagerImpl implements ElectricityMessageManager {

	/** The Constant logger. */
	final private static Logger logger= LoggerFactory.getLogger(ElectricityMessageManagerImpl.class);
	
	// Object to send message to activemq Topic service.
	@Autowired
	@Qualifier("jmsTemplateTopic")
	JmsTemplate jmsTemplateTopic;
	
	// Object to send message to activemq Queue service.
	@Autowired
	@Qualifier("jmsTemplateQueue")
	JmsTemplate jmsTemplateQueue;
	
	/**
	 * Send message to activemq.
	 *  
	 *  information[0] = ID user
	 *  information[1] = Date of created.
	 *  information[2] = Khw identifyed.
	 *  information[3] = 
	 *  
	 */
	@Override
	public void sendMessage(ACTIVEMQ type, String... information) {
		
		
		try{
			
			//Mount the message to send to activemq server
			ElectricityMessage message = new ElectricityMessage(information[0],new Timestamp(Long.parseLong(information[1])),new Double(information[2]));
			
			//VErify what type of service:
			if(type == ACTIVEMQ.QUEUE){
				jmsTemplateQueue.convertAndSend(message);
				
			}else{
				jmsTemplateTopic.convertAndSend(message);
				
			}
			
		}catch (Exception exce){
			logger.error(exce.getMessage(),exce);
			throw ErrorHandler.processExcpetion(exce);
		}
	}

}

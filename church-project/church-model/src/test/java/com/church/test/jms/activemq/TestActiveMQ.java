package com.church.test.jms.activemq;

import org.church.core.message.ACTIVEMQ;
import org.church.core.message.ElectricityMessageManager;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.church.model.applicationConf.ApplicationConfig;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestActiveMQ {
	
	
	@Autowired
	ElectricityMessageManager messageACT;
	
	@Test
	public void sendMessageTest(){
		
		
//		information[0] = ID user
//				 *  information[1] = Date of created.
//				 *  information[2] = Khw identifyed.
//				 *  information[3] = 
		long time = System.currentTimeMillis();
		messageACT.sendMessage(ACTIVEMQ.TOPIC, "1",String.valueOf(time),"20");
		
	}
	
}

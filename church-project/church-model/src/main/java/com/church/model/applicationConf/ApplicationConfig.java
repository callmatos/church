package com.church.model.applicationConf;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ImportResource({"classpath*:META-INF/spring/applicationContext-model.xml" })
@PropertySources({
	@PropertySource( value = "file:${ELECTRICITY_CONSUME_PATH}/conf/application.properties, file:/home/electricity_consume/conf/application.properties", ignoreResourceNotFound = true),
	@PropertySource( value = "classpath:META-INF/local/application.properties"),
})

@Import({ PersistenceContext.class })
public class ApplicationConfig {

	private static final String PROPERTY_NAME_ACTIVEMQ_URL = "activemq.url";
	private static final String PROPERTY_NAME_ACTIVEMQ_APPLI_TOPIC = "activemq.appli.topic";
//	private static final String PROPERTY_NAME_ACTIVEMQ_MIMPOOLSIZE = "activemq.minPoolSize";
//	private static final String PROPERTY_NAME_ACTIVEMQ_MAXPOOLSIZE = "activemq.maxPoolSize";
	private static final String PROPERTY_NAME_ACTIVEMQ_APPLI_MQUEUE = "activemq.default.queue";
	private static final String PROPERTY_NAME_ACTIVEMQ_SEND_TIMEOUT = "activemq.sendtimeout";
	
	@Resource
	private Environment env;
	
	
	@Bean(destroyMethod = "stop")
	public PooledConnectionFactory jmsConnectionFactory(){
		
		//Get the reference the connection factory
		final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		
		//Configure factory
		factory.setBrokerURL(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_URL));
		factory.setSendTimeout(Integer.parseInt(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_SEND_TIMEOUT)));

		return new PooledConnectionFactory(factory);
	}
	
	@Bean
	public Topic electrictyTopic(){
		return new ActiveMQTopic(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_APPLI_TOPIC));
	}
	
	@Bean
	public Queue electrictyQueue(){
		return new ActiveMQQueue(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_APPLI_MQUEUE));
	}
	
	
	@Bean(name="jmsTemplateTopic")
	public JmsOperations jmsTemplateTopic(){
		
		//Define the JmsTemplate
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory());
		jmsTemplate.setDefaultDestination(electrictyTopic());
		jmsTemplate.setReceiveTimeout(Integer.parseInt(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_SEND_TIMEOUT)));
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setExplicitQosEnabled(true);
		return jmsTemplate;
	}
	
	@Bean(name="jmsTemplateQueue")
	public JmsOperations jmsTemplateQueue(){
		
		//Define the JmsTemplate
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory());
		jmsTemplate.setDefaultDestination(electrictyQueue());
		jmsTemplate.setReceiveTimeout(Integer.parseInt(env.getRequiredProperty(PROPERTY_NAME_ACTIVEMQ_SEND_TIMEOUT)));
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setExplicitQosEnabled(true);
		return jmsTemplate;
	}
	
	
	
	
	
}

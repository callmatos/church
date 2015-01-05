package com.church.model.applicationConf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "com.church.model")
@Import({ PersistenceContext.class })
public class ApplicationConfig {

	private static final String PROPERTY_MESSAGE = "message.source.basename";
	
	@Bean
	public ResourceBundleMessageSource messageSource(Environment env) {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(env.getRequiredProperty(PROPERTY_MESSAGE));
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}

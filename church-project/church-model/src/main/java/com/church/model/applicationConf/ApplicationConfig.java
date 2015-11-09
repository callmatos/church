package com.church.model.applicationConf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ImportResource({"classpath*:META-INF/spring/applicationContext-model.xml" })
@PropertySources({
	@PropertySource( value = "file:${ELECTRICITY_CONSUME_PATH}/conf/application.properties, file:/home/electricity_consume/conf/application.properties", ignoreResourceNotFound = true),
	@PropertySource( value = "classpath:META-INF/local/application.properties"),
})

@Import({ PersistenceContext.class })
public class ApplicationConfig {


	
	

}

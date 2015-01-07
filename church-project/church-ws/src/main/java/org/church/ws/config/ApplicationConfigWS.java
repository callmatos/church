package org.church.ws.config;

import org.church.ws.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.church.model.applicationConf.ApplicationConfig;

@Configuration
@ComponentScan(basePackages = "org.church.ws")
@Import({ApplicationConfig.class})
@ImportResource({ "classpath*:META-INF/applicationsConfig.xml" })
@EnableWebMvc
public class ApplicationConfigWS{

	@Autowired
	private App myapp;
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] resources = { "classpath:labels", "classpath:message" };
		messageSource.setBasenames(resources);
		return messageSource;
	}
}

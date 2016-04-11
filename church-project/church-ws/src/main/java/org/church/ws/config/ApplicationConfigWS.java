package org.church.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.church.model.applicationConf.ApplicationConfig;

@Configuration
@ComponentScan(basePackages = "org.church.ws")
@Import({ApplicationConfig.class})
@ImportResource({ "classpath*:META-INF/applicationsConfig.xml" })
@EnableWebMvc
@EnableSpringDataWebSupport
public class ApplicationConfigWS{

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] resources = { "classpath:labels", "classpath:message" };
		messageSource.setBasenames(resources);
		return messageSource;
	}
}

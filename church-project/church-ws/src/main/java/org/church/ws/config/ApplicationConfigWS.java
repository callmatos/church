package org.church.ws.config;

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
//@ContextConfiguration( classes = {ApplicationConfig.class})
@Import({ ApplicationConfig.class })
@ImportResource({ "classpath*:META-INF/applicationsConfigWs.xml" })
@EnableWebMvc
//@EnableAutoConfiguration
public class ApplicationConfigWS{

//	public static void main(String[] args){
//		SpringApplication.run(ApplicationConfigWS.class, args);
//	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] resources = { "classpath:labels", "classpath:message" };
		messageSource.setBasenames(resources);
		return messageSource;
	}
}

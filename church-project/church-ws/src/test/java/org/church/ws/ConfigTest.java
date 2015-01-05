package org.church.ws;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("org.church.ws")
public class ConfigTest extends WebMvcConfigurerAdapter {

    public ConfigTest() {
        super();
    }

    // API

}

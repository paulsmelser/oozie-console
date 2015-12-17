package com.smelser.oozie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by paul.smelser@gmail.com on 03/10/15.
 * @author psmelser
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:oozie-web.properties")
public class OozieWebApplication {

    public static void main(String [] args){
        SpringApplication.run(OozieWebApplication.class, args);
    }

    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);

        return resolver;
    }
}

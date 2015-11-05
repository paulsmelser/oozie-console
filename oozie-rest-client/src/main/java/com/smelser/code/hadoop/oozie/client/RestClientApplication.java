package com.smelser.code.hadoop.oozie.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by paul.smelser@gmail.com on 03/10/15.
 * @author psmelser
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RestClientApplication {

    public static void main(String [] args){
        SpringApplication.run(RestClientApplication.class, args);
    }
}

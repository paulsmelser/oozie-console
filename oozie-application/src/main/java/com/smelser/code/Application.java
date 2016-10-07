package com.smelser.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.util.StopWatch;

/**
 * Created by paul.smelser@gmail.com on 03/10/15.
 * @author psmelser
 */
@SpringBootApplication
@EnableRetry
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public StopWatch stopWatch(){
        return new StopWatch();
    }

}

package com.smelser.oozie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import com.smelser.code.hadoop.oozie.client.utils.JsonUtils;

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

}

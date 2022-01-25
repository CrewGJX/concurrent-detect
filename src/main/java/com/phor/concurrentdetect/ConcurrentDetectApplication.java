package com.phor.concurrentdetect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class ConcurrentDetectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrentDetectApplication.class, args);
    }

}

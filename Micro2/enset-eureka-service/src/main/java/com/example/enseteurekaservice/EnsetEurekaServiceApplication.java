package com.example.enseteurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EnsetEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run
                (EnsetEurekaServiceApplication.class, args);
    }

}

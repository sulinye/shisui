package com.su.shisui.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.su.shisui")
@EnableEurekaClient
@SpringBootApplication
public class ShisuiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShisuiAuthApplication.class, args);
    }

}

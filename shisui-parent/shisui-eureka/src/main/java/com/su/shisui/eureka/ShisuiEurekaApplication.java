package com.su.shisui.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ShisuiEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShisuiEurekaApplication.class, args);
    }

}

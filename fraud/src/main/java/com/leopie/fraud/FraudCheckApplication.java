package com.leopie.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FraudCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudCheckApplication.class, args);
    }
}

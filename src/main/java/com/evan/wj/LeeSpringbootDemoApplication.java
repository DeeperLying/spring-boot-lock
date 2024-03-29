package com.evan.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LeeSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeSpringbootDemoApplication.class, args);
    }

}

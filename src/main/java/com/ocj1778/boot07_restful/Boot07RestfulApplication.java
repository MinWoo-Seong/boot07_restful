package com.ocj1778.boot07_restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class Boot07RestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot07RestfulApplication.class, args);
    }

}

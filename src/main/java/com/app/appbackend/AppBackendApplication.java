package com.app.appbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin()
@SpringBootApplication
public class AppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBackendApplication.class, args);
    }

}

package com.app.appbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AppBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(AppBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner setUp() throws IOException {

        return (args) -> {
            new File("../images").mkdirs();
        };
    }
}

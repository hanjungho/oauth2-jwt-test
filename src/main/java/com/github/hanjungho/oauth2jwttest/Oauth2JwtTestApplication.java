package com.github.hanjungho.oauth2jwttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.github.hanjungho.oauth2jwttest.domain.entity")
public class Oauth2JwtTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtTestApplication.class, args);
    }

}

package com.husen.twelvemessagecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwelveMessageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwelveMessageCenterApplication.class, args);
    }
}

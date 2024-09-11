package com.jhta.hta_miniproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HtaMiniproject1Application {

    public static void main(String[] args) {
        SpringApplication.run(HtaMiniproject1Application.class, args);
    }

}

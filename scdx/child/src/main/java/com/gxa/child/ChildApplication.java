package com.gxa.child;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.gxa.child.dao")
public class ChildApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildApplication.class, args);
    }

}

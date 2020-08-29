package com.zy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.zy.entity")
@EnableJpaRepositories("com.zy.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}

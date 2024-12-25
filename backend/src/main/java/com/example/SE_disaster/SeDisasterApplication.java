package com.example.SE_disaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.SE_disaster.mappers")
public class SeDisasterApplication {

    public static void main(String[] args) {
        // System.out.println(SpringBootVersion.getVersion());
        SpringApplication.run(SeDisasterApplication.class, args);
    }

}

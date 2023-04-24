package com.game;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhaolianjie
 * @date 2022年12月07日 11:12
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
@MapperScan("com.game.core.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);


    }
}

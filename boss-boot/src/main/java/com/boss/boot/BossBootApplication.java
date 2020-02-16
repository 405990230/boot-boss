package com.boss.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BossBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BossBootApplication.class, args);

//        SpringApplication app = new SpringApplication(BossBootApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
    }

}

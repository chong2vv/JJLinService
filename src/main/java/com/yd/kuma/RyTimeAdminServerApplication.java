package com.yd.kuma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author gongjiguang
 */
@SpringBootApplication
@EnableScheduling
public class RyTimeAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RyTimeAdminServerApplication.class, args);
    }

}

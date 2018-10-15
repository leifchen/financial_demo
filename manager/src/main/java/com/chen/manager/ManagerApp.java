package com.chen.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * ManagerApp
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@SpringBootApplication
@EntityScan(basePackages = "com.chen.entity")
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class);
    }
}

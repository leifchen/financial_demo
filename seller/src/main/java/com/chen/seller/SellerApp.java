package com.chen.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 销售端启动类
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@SpringBootApplication(scanBasePackages = "com.chen",
        exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }
}

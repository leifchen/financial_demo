package com.chen.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

/**
 * Swagger配置信息
 *
 * @Author LeifChen
 * @Date 2018-10-17
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

    private String groupName = "controller";
    private String basePackage;
    private String antPath;
    private String title = "HTTP API";
    private String description = "管理端接口";
    private String version = "1.0";
    private Contact contact = new Contact("LeifChen", "https://github.com/leifchen", "lefengchen@163.com");
}

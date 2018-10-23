package com.chen.swagger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
@Configuration
@ComponentScan(basePackages = "com.chen.swagger")
@EnableSwagger2
public class SwaggerConfiguration {

    private final SwaggerInfo swaggerInfo;

    public SwaggerConfiguration(SwaggerInfo swaggerInfo) {
        this.swaggerInfo = swaggerInfo;
    }

    @Bean
    public Docket controllerApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .apiInfo(apiInfo());
        ApiSelectorBuilder apiBuilder = docket.select();
        if (!StringUtils.isEmpty(swaggerInfo.getBasePackage())) {
            apiBuilder = apiBuilder.apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()));
        }
        if (!StringUtils.isEmpty(swaggerInfo.getAntPath())) {
            apiBuilder = apiBuilder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
        }

        return apiBuilder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerInfo.getTitle())
                .description(swaggerInfo.getDescription())
                .version(swaggerInfo.getVersion())
                .contact(swaggerInfo.getContact())
                .build();
    }
}

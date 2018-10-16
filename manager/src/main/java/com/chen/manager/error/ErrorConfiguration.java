package com.chen.manager.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ErrorConfiguration
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public MyErrorController basicErrorController(ErrorAttributes errorAttributes,
                                                  ServerProperties serverProperties,
                                                  ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {

        return new MyErrorController(errorAttributes,
                serverProperties.getError(),
                errorViewResolversProvider.getIfAvailable());
    }
}

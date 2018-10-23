package com.chen.util.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * JSON-RPC 相关配置
 *
 * @Author LeifChen
 * @Date 2018-10-23
 */
@Slf4j
@Configuration
public class JsonRpcConfiguration {

    @Bean
    public static AutoJsonRpcServiceImplExporter rpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }

    @Bean
    @ConditionalOnProperty(value = {"rpc.client.url", "rpc.client.basePackage"})
    public static AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String url, @Value("${rpc.client.basePackage}") String basePackage) {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("创建RPC服务地址错误", e);
        }
        creator.setScanPackage(basePackage);
        return creator;
    }
}

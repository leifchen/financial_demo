package com.chen.manager.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 相关配置
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@Configuration
public class RpcConfiguration {

    @Bean
    public static AutoJsonRpcServiceImplExporter rpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }
}

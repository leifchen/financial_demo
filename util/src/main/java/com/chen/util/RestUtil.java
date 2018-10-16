package com.chen.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Rest工具类
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
@Slf4j
public class RestUtil {

    /**
     * 发送POST请求
     *
     * @param restTemplate
     * @param url
     * @param param
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postJSON(RestTemplate restTemplate, String url, Object param, Class<T> responseType) {
        HttpEntity<String> formEntity = makePostJSONEntity(param);
        T result = restTemplate.postForObject(url, formEntity, responseType);
        log.info("rest-post-json 响应信息:{}", JsonUtil.toJson(result));
        return result;
    }

    /**
     * 生成Json形式的请求头
     *
     * @param param
     * @return
     */
    public static HttpEntity<String> makePostJSONEntity(Object param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> formEntity = new HttpEntity<>(
                JsonUtil.toJson(param), headers);
        log.info("rest-post-json-请求参数:{}", formEntity.toString());
        return formEntity;
    }

    public static HttpEntity<String> makePostTextEntity(Map<String, ? extends Object> param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> formEntity = new HttpEntity<>(
                makeGetParamContent(param), headers);
        log.info("rest-post-text-请求参数:{}", formEntity.toString());
        return formEntity;
    }

    /**
     * 生成GET请求内容
     *
     * @param param
     * @param excluedes
     * @return
     */
    public static String makeGetParamContent(Map<String, ? extends Object> param, String... excluedes) {
        StringBuilder content = new StringBuilder();
        param.forEach((key, v) -> content.append(key).append("=").append(v).append("&"));
        if (content.length() > 0) {
            content.deleteCharAt(content.length() - 1);
        }
        return content.toString();
    }
}
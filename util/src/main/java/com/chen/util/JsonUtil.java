package com.chen.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DateFormat;

/**
 * Json工具类
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
@Slf4j
public class JsonUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        // 属性可见度只打印public
//     MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static void setDateFormat(DateFormat dateFormat) {
        MAPPER.setDateFormat(dateFormat);
    }

    /**
     * 把Java对象转为JSON字符串
     *
     * @param obj the object need to transfer into json string.
     * @return json string.
     */
    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("to json exception.", e);
            throw new JsonException("把对象转换为JSON时出错了", e);
        }
    }
}

final class JsonException extends RuntimeException {
    public JsonException(final String message) {
        super(message);
    }

    public JsonException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

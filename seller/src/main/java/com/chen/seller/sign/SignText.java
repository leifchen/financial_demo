package com.chen.seller.sign;

import com.chen.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 签名明文
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {

    /**
     * 生成明文字符串
     *
     * @return
     */
    default String toText() {
        return JsonUtil.toJson(this);
    }
}

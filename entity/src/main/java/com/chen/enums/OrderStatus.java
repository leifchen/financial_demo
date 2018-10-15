package com.chen.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
public enum OrderStatus {
    /**
     * 初始化
     */
    INIT("初始化"),
    /**
     * 处理中
     */
    PROCESS("处理中"),
    /**
     * 成功
     */
    SUCCESS("成功"),
    /**
     * 失败
     */
    FAIL("失败");

    @Getter
    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }
}

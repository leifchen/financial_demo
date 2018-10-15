package com.chen.enums;

import lombok.Getter;

/**
 * 订单类型
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
public enum OrderType {
    /**
     * 申购
     */
    APPLY("申购"),
    /**
     * 赎回
     */
    REDEEM("赎回");

    @Getter
    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }
}

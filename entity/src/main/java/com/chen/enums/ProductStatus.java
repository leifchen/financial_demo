package com.chen.enums;

import lombok.Getter;

/**
 * 产品状态
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
public enum ProductStatus {
    /**
     * 审核中
     */
    AUDITING("审核中"),
    /**
     * 销售中
     */
    IN_SELL("销售中"),
    /**
     * 暂停销售
     */
    LOCKED("暂停销售"),
    /**
     * 已结束
     */
    FINISHED("已结束");

    @Getter
    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }
}

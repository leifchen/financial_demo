package com.chen.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@Data
@Entity(name = "t_order")
public class Order {

    /**
     * 订单 id
     */
    @Id
    private String orderId;
    /**
     * 渠道 id
     */
    private String channelId;
    /**
     * 产品 id
     */
    private String productId;
    /**
     * 渠道用户 id
     */
    private String channelUserId;
    /**
     * 订单类型
     *
     * @see com.chen.enums.OrderType
     */
    private String orderType;
    /**
     * 订单状态
     *
     * @see com.chen.enums.OrderStatus
     */
    private String orderStatus;
    /**
     * 外部订单 id
     */
    private String outerOrderId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", productId='" + productId + '\'' +
                ", channelUserId='" + channelUserId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", outerOrderId='" + outerOrderId + '\'' +
                ", amount=" + amount +
                ", memo='" + memo + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}

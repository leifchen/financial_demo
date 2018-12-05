package com.chen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 对账订单
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@Data
@Entity(name = "verification_order")
public class VerificationOrder {

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
     * 外部订单 id
     */
    private String outerOrderId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
}

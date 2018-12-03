package com.chen.seller.params;

import com.chen.seller.sign.SignText;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 下单请求参数
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Getter
@Setter
public class OrderParam implements SignText {

    /**
     * 渠道 id
     */
    private String channelId;
    /**
     * 渠道用户 id
     */
    private String channelUserId;
    /**
     * 产品 id
     */
    private String productId;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
}

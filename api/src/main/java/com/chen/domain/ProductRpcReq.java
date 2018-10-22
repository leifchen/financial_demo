package com.chen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品相关 RPC 请求对象
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@Getter
@Setter
@ToString
public class ProductRpcReq {

    private List<String> idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> statusList;
}

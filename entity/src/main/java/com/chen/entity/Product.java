package com.chen.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@Getter
@Setter
@Entity(name = "t_product")
public class Product implements Serializable {

    /**
     * 产品 id
     */
    @Id
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     *
     * @see com.chen.enums.ProductStatus
     */
    private String status;
    /**
     * 起投金额
     */
    private BigDecimal thresholdAmount;
    /**
     * 投资步长
     */
    private BigDecimal stepAmount;
    /**
     * 锁定期
     */
    private Integer lockTerm;
    /**
     * 收益率
     */
    private BigDecimal rewardRate;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 更新者
     */
    private String updateUser;

    public Product() {
    }

    public Product(String id, String name, String status, BigDecimal thresholdAmount, BigDecimal stepAmount, Integer lockTerm, BigDecimal rewardRate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.thresholdAmount = thresholdAmount;
        this.stepAmount = stepAmount;
        this.lockTerm = lockTerm;
        this.rewardRate = rewardRate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", stepAmount=" + stepAmount +
                ", lockTerm=" + lockTerm +
                ", rewardRate=" + rewardRate +
                ", memo='" + memo + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", createUser='" + createUser + '\'' +
                ", gmtModified=" + gmtModified +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}

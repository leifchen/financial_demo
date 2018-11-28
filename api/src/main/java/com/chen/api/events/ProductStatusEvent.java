package com.chen.api.events;

import com.chen.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 产品状态事件
 *
 * @Author LeifChen
 * @Date 2018-11-28
 */
@Getter
@Setter
@ToString
public class ProductStatusEvent implements Serializable {

    private String id;
    private ProductStatus status;

    public ProductStatusEvent(String id, ProductStatus status) {
        this.id = id;
        this.status = status;
    }
}

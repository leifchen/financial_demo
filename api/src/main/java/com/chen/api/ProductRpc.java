package com.chen.api;

import com.chen.api.domain.ProductRpcReq;
import com.chen.entity.Product;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 产品相关 RPC 服务接口
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@JsonRpcService("rpc/product")
@Component
public interface ProductRpc {

    /**
     * 查询多个产品
     *
     * @param req
     * @return
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     *
     * @param id
     * @return
     */
    Product getOne(String id);
}

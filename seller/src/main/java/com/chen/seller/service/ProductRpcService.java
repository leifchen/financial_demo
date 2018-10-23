package com.chen.seller.service;

import com.chen.api.ProductRpc;
import com.chen.domain.ProductRpcReq;
import com.chen.entity.Product;
import com.chen.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品相关 RPC 服务
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@Service
@Slf4j
public class ProductRpcService {

    private final ProductRpc productRpc;

    public ProductRpcService(ProductRpc productRpc) {
        this.productRpc = productRpc;
    }

    /**
     * 查询所有产品
     *
     * @return 产品列表
     */
    public List<Product> listAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.AUDITING.name());
        req.setStatusList(status);
        log.info("RPC查询全部产品，请求：{}", req);
        List<Product> result = productRpc.query(req);
        log.info("RPC查询全部产品，结果：{}", result);
        return result;
    }

    /**
     * 查询单个产品
     *
     * @return 单个产品
     */
    public Product getOne(String id) {
        log.info("RPC查询单个产品，请求：{}", id);
        Product result = productRpc.getOne(id);
        log.info("RPC查询单个产品，结果：{}", result);
        return result;
    }
}

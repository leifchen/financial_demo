package com.chen.manager.rpc;

import com.chen.api.ProductRpc;
import com.chen.api.domain.ProductRpcReq;
import com.chen.entity.Product;
import com.chen.manager.service.ProductService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品相关 RPC 服务实现类
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@AutoJsonRpcServiceImpl
@Service
@Slf4j
public class ProductRpcImpl implements ProductRpc {

    private final ProductService productService;

    public ProductRpcImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> query(ProductRpcReq req) {
        log.info("查询多个产品，请求：{}", req);
        Pageable pageable = PageRequest.of(0, 1000, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate(), req.getMaxRewardRate(), req.getStatusList(), pageable);
        log.info("查询多个产品，结果：{}", result);
        return result.getContent();
    }

    @Override
    public Product getOne(String id) {
        log.info("查询产品详情，请求：{}", id);
        Product result = productService.getOne(id);
        log.info("查询产品详情，结果：{}", result);
        return result;
    }
}

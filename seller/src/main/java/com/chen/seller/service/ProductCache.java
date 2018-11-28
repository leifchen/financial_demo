package com.chen.seller.service;

import com.chen.api.ProductRpc;
import com.chen.api.domain.ProductRpcReq;
import com.chen.entity.Product;
import com.chen.enums.ProductStatus;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品缓存
 *
 * @Author LeifChen
 * @Date 2018-10-24
 */
@Slf4j
@Component
public class ProductCache {

    static final String CACHE_NAME = "financial_product";

    private final ProductRpc productRpc;
    private final HazelcastInstance hazelcastInstance;

    public ProductCache(ProductRpc productRpc,
                        HazelcastInstance hazelcastInstance) {
        this.productRpc = productRpc;
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * 读取缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        log.info("RPC查询单个产品，请求：{}", id);
        Product result = productRpc.getOne(id);
        log.info("RPC查询单个产品，结果：{}", result);
        return result;
    }

    /**
     * 更新缓存
     *
     * @param product
     * @return
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public void putCache(Product product) {
    }

    /**
     * 清除缓存
     *
     * @param id
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {
        log.info("清除缓存，key：{}", id);
    }

    /**
     * 从缓存读取所有产品
     *
     * @return
     */
    public List<Product> readAllCache() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products;
        if (map.size() > 0) {
            products = new ArrayList<>(map.values());
        } else {
            products = listAll();
        }
        return products;
    }

    /**
     * 查询所有产品
     *
     * @return
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
}

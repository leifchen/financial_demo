package com.chen.seller.service;

import com.chen.api.events.ProductStatusEvent;
import com.chen.entity.Product;
import com.chen.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品相关 RPC 服务
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@Service
@Slf4j
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {
    static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";

    private final ProductCache productCache;

    public ProductRpcService(ProductCache productCache) {
        this.productCache = productCache;
    }

    /**
     * 查询所有产品
     *
     * @return 产品列表
     */
    public List<Product> listAll() {
        return productCache.readAllCache();
    }

    /**
     * 查询单个产品
     *
     * @return 单个产品
     */
    public Product getOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return product;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> products = listAll();
        products.forEach(productCache::putCache);
    }

    @JmsListener(destination = MQ_DESTINATION)
    public void updateCache(ProductStatusEvent event) {
        log.info("Receive event:{}", event);
        productCache.removeCache(event.getId());
        if (ProductStatus.IN_SELL.equals(event.getStatus())) {
            productCache.readCache(event.getId());
        }
    }
}

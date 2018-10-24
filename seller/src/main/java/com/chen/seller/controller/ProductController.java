package com.chen.seller.controller;

import com.chen.entity.Product;
import com.chen.seller.service.ProductRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品相关控制器
 *
 * @Author LeifChen
 * @Date 2018-10-24
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRpcService productRpcService;

    public ProductController(ProductRpcService productRpcService) {
        this.productRpcService = productRpcService;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable String id) {
        return productRpcService.getOne(id);
    }
}

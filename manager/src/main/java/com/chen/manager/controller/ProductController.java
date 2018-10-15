package com.chen.manager.controller;

import com.chen.entity.Product;
import com.chen.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 产品 Controller
 *
 * @Author LeifChen
 * @Date 2018-10-01
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        log.info("创建产品，参数:{}", product);
        Product result = productService.addProduct(product);
        log.info("创建产品，结果={}", result);
        return result;
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        log.info("查询单个产品，id={}", id);
        Product result = productService.findOne(id);
        log.info("查询单个产品，结果={}", result);
        return result;
    }

    @GetMapping
    public Page<Product> query(String ids,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               String status,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {

        log.debug("查询产品，ids={}，minRewardRate={}，maxRewardRate={}，status={}，pageNum={}，pageSize={}", ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Product> result = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);
        log.info("查询产品，结果={}", result);
        return result;
    }
}

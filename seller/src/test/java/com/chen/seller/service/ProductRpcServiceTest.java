package com.chen.seller.service;

import com.chen.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * ProductRpcServiceTest
 *
 * @Author LeifChen
 * @Date 2018-10-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRpcServiceTest {

    @Autowired
    private ProductRpcService productRpcService;

    @Test
    public void listAll() {
        List<Product> products = productRpcService.listAll();
        Assert.notEmpty(products,"查询所有产品失败");
    }

    @Test
    public void getOne() {
        Product product = productRpcService.getOne("T001");
        Assert.notNull(product,"查询单个产品失败");
    }
}
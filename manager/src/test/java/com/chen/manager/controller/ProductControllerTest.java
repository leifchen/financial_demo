package com.chen.manager.controller;

import com.chen.entity.Product;
import com.chen.enums.ProductStatus;
import com.chen.util.RestUtil;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductControllerTest
 *
 * @Author LeifChen
 * @Date 2018-10-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

    private static RestTemplate rest = new RestTemplate();

    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    /**
     * 正常产品数据
     */
    private static List<Product> normals = new ArrayList<>();

    /**
     * 异常产品数据
     */
    private static List<Product> exceptions = new ArrayList<>();

    @BeforeClass
    public static void init() {
        Product p1 = new Product("T001", "金融1号", ProductStatus.AUDITING.name(), BigDecimal.valueOf(100), BigDecimal.valueOf(100), 7, BigDecimal.valueOf(3.14));
        Product p2 = new Product("T002", "金融2号", ProductStatus.AUDITING.name(), BigDecimal.valueOf(500), BigDecimal.valueOf(100), 7, BigDecimal.valueOf(4.2));
        Product p3 = new Product("T003", "金融3号", ProductStatus.AUDITING.name(), BigDecimal.valueOf(1000), BigDecimal.valueOf(500), 21, BigDecimal.valueOf(6.6));
        normals.add(p1);
        normals.add(p2);
        normals.add(p3);

        Product e1 = new Product(null, "编号不可为空", ProductStatus.AUDITING.name(), BigDecimal.valueOf(100), BigDecimal.valueOf(100), 7, BigDecimal.valueOf(3.14));
        Product e2 = new Product("E002", "收益率范围错误", ProductStatus.AUDITING.name(), BigDecimal.valueOf(500), BigDecimal.valueOf(100), 7, BigDecimal.valueOf(36));
        Product e3 = new Product("E003", "投资步长需为整数", ProductStatus.AUDITING.name(), BigDecimal.valueOf(1000), BigDecimal.valueOf(500.5), 21, BigDecimal.valueOf(6.6));
        exceptions.add(e1);
        exceptions.add(e2);
        exceptions.add(e3);

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) {

            }
        };
        rest.setErrorHandler(errorHandler);
    }

    @Test
    public void create() {
        normals.forEach(product -> {
            Product result = RestUtil.postJSON(rest, baseUrl + "/product", product, Product.class);
            Assert.notNull(result.getGmtCreate(), "插入失败");
        });
    }

    @Test
    public void createException() {
        exceptions.forEach(product -> {
            Map<String, String> result = RestUtil.postJSON(rest, baseUrl + "/product", product, HashMap.class);
            System.out.println(result);
            Assert.isTrue(result.get("message").equals(product.getName()), "插入成功");
        });
    }

    @Test
    public void findOne() {
        normals.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/product/" + product.getId(), Product.class);
            Assert.notNull(result, "查询失败");
        });

        exceptions.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/product/" + product.getId(), Product.class);
            Assert.isNull(result, "查询失败");
        });
    }
}
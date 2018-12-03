package com.chen.seller.controller;

import com.chen.entity.Order;
import com.chen.seller.params.OrderParam;
import com.chen.seller.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 订单 Controller
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 下单
     *
     * @param param
     * @return
     */
    @PostMapping("/apply")
    public Order apply(@RequestHeader String authId,
                       @RequestHeader String sign,
                       @RequestBody OrderParam param) {

        log.info("申购请求：{}", param);
        Order order = new Order();
        BeanUtils.copyProperties(param, order);
        order = orderService.apply(order);
        log.info("申购结果：{}", order);
        return order;
    }
}

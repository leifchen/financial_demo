package com.chen.seller.service;

import com.chen.entity.Order;
import com.chen.entity.Product;
import com.chen.enums.OrderStatus;
import com.chen.enums.OrderType;
import com.chen.seller.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 订单 Service
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRpcService productRpcService;

    public OrderService(OrderRepository orderRepository, ProductRpcService productRpcService) {
        this.orderRepository = orderRepository;
        this.productRpcService = productRpcService;
    }

    /**
     * 申购订单
     *
     * @param order
     * @return
     */
    public Order apply(Order order) {
        // 数据校验
        checkOrder(order);
        // 完善订单数据
        completeOrder(order);
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    /**
     * 校验数据
     *
     * @param order
     */
    private void checkOrder(Order order) {
        // 必填字段
        Assert.notNull(order.getOuterOrderId(), "需要外表订单编号");
        Assert.notNull(order.getChannelId(), "需要渠道编号");
        Assert.notNull(order.getChannelUserId(), "需要用户编号");
        Assert.notNull(order.getProductId(), "需要产品编号");
        Assert.notNull(order.getAmount(), "需要购买金额");
        Assert.notNull(order.getGmtCreate(), "需要订单时间");

        // 产品是否存在及金额是否符合要求
        Product product = productRpcService.getOne(order.getProductId());
        Assert.notNull(product, "产品不存在");
        // 金额要满足起投金额
        Assert.isTrue(order.getAmount().compareTo(product.getThresholdAmount()) >= 0, "金额需大于产品的起投金额");
    }

    /**
     * 完善订单数据
     *
     * @param order
     */
    private void completeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setGmtModified(new Date());
    }
}

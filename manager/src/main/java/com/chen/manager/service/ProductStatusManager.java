package com.chen.manager.service;

import com.chen.api.events.ProductStatusEvent;
import com.chen.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 管理产品状态
 *
 * @Author LeifChen
 * @Date 2018-11-28
 */
@Slf4j
@Component
public class ProductStatusManager {

    static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    private final JmsTemplate jmsTemplate;

    public ProductStatusManager(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void changeStatus(String id, ProductStatus status) {
        ProductStatusEvent event = new ProductStatusEvent(id, status);
        log.info("Send message:{}", event);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
    }

    @PostConstruct
    public void init() {
        changeStatus("T001", ProductStatus.FINISHED);
    }
}

package com.crawler.config.rabbitmq;

import com.crawler.config.rabbitmq.util.RabbitMqEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * rabbitmq发送消息工具类
 */
@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("confirm: " + correlationData.getId());
    }

    /**
     * 发送到 指定routekey的指定queue
     *
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqDirect(String routeKey, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CRAWER_E.getCode(), routeKey, obj, correlationData);
    }

}

package com.crawler.config.rabbitmq.util;

import com.crawler.config.rabbitmq.mqconfig.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq 生产者配置
 * 用于配置交换机和队列对应关系
 * 新增消息队列应该按照如下步骤
 * 1、增加queue bean，参见queueXXXX方法
 * 2、增加queue和exchange的binding
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqExchangeConfig {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqExchangeConfig.class);


    /**
     * 直连型交换机
     */
    @Bean
    DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(RabbitMqEnum.Exchange.CRAWER_E.getCode());
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("DirectExchange bean实例化");
        return contractDirectExchange;
    }

    //在此可以定义队列
    @Bean
    Queue queueCrawer(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitMqEnum.QueueName.CRAWER.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("queueCrawer队列实例化完成");
        return queue;
    }

    //在此处完成队列和交换机绑定
    @Bean
    Binding bindingQueue(Queue queue, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(RabbitMqEnum.QueueEnum.Routing_key.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("queueCrawer与DirectExchange绑定完成");
        return binding;
    }


}

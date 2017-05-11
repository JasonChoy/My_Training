package com.jason.demo.productMQ.Impl;

import com.jason.demo.productMQ.ITestProduct;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/11.
 */
@Component
public class TestProduct implements ITestProduct {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDataToQueue(String queueKey, Object object) {
        amqpTemplate.convertAndSend(queueKey,object);
    }
}
/*
convertAndSend：将Java对象转换为消息发送到匹配Key的交换机中Exchange，由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。
        原文：Convert a Java object to an Amqp Message and send it to a default exchange with a specific routing key.*/

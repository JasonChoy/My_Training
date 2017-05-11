package com.jason.demo.consumerMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by Administrator on 2017/5/11.
 */
public class TestConsumerListener implements MessageListener {
    public void onMessage(Message message) {
        System.out.println(message);
    }
}

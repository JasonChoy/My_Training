package com.jason.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class Receiver {
    @Autowired
    JmsTemplate jmsTemplate;
    @Test
    public void receiver() {
        while (true) {
            Map<String, Object> map = (Map<String, Object>) jmsTemplate.receiveAndConvert();
            System.out.println("收到消息：" + map.get("message"));
        }
    }
}

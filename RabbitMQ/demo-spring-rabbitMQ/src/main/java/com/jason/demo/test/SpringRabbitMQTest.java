package com.jason.demo.test;

import com.jason.demo.domain.User;
import com.jason.demo.productMQ.ITestProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class SpringRabbitMQTest {
    @Autowired
    ITestProduct testProduct;

    @Test
    public void test(){
        String queuekey = "test_queue_key";
        User user = new User(1, "jasoncai", new Date());
        Map<String,Object> msg = new HashMap<String,Object>();
        msg.put("data","hello,rabbmitmq!");
        testProduct.sendDataToQueue(queuekey,user);

/*        int a = 100;
        while (a > 0) {
            testProduct.sendDataToQueue(queuekey,"Hello, I am amq sender num :" + a--);
            try {
                //暂停一下，好让消息消费者去取消息打印出来
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/
    }

}

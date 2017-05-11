package com.jason.demo.productMQ;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface ITestProduct {
    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    void sendDataToQueue(String queueKey, Object object);
}

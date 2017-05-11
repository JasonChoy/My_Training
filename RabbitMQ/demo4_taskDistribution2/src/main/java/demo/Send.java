package demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/5/10.
 * Product生产者
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setHost("127.0.0.1");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个频道
        Channel channel = connection.createChannel();
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        //开启公平分发任务机制
        int prefetchCount = 1;
        //限制发给同一个消费者不得超过1条消息
        channel.basicQos(prefetchCount);


        // 发送的消息
        // 往队列中发出一条消息
        for (int i = 0; i < 10; i++) {
            // 发送的消息
            String message = "Hello World"+ repeat(".", i) + i;
            // 往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }


    public static String repeat(String str ,int num){
        String result = "";
        for (int i = 0; i < num; i++) {
            result += str;
        }
        return result;
    }
}

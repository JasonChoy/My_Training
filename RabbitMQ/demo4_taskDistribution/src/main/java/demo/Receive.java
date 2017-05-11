package demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/5/10.
 *  Consumer 消费者
 *  使用Thread.Sleep()方法来模拟耗时。采用小数点的数量来表示任务的复杂性。每一个点将住哪用1s的“工作”。例如，Hello... 处理完需要3s的时间。
 */
public class Receive {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                System.out.println(" [x] Proccessing... at " +new Date().toLocaleString());
                try {
                    for (char ch: message.toCharArray()) {
                        if (ch == '.') {
                            Thread.sleep(1000);
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    System.out.println(" [x] Done! at " +new Date().toLocaleString());
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

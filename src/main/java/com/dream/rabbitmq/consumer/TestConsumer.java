package com.dream.rabbitmq.consumer;

import com.dream.rabbitmq.config.RabbitmqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;

/**
 * @author: wubo
 * @Date: 2019-01-23
 * @Time: 13:37
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Component
public class TestConsumer {
    @RabbitListener(queues = RabbitmqConstant.TEST_QUEUE)
    public void messageConsumer(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws Exception {
        System.out.println("消费者端接收到消息 : " + message + "---------------------- tag : " + tag);
        channel.basicAck(tag, false);
        //channel.basicNack(tag, false, true);
        // channel.basicReject(tag, true);
    }
}

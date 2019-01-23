package com.dream.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.rabbitmq.Message;
import com.dream.rabbitmq.config.RabbitmqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: wubo
 * @Date: 2019-01-23
 * @Time: 13:48
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Component
@Slf4j
public class TestProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setup() {
        // 消息发送完毕后，则回调此方法 ack代表发送是否成功
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String orderId = correlationData.getId();
            // ack为true，代表MQ已经准确收到消息
            if (!ack) {
                return;
            }
            System.out.println("消息发送成功 : " + orderId);
        });
    }

    /**
     * 发送MQ消息，修改本地消息表的状态
     *
     * @throws Exception
     */
    public void sendMsg(Message msg) {
        // 1. 发送消息到MQ
        // CorrelationData 当收到消息回执时，会附带上这个参数
        rabbitTemplate.convertAndSend(RabbitmqConstant.TEST_EXCHANGE, "", JSON.toJSONString(msg),
                new CorrelationData(msg.getOrderId()));
    }
}

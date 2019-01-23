package com.dream.rabbitmq.controller;

import com.dream.rabbitmq.Message;
import com.dream.rabbitmq.producer.TestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wubo
 * @Date: 2019-01-23
 * @Time: 13:57
 * @Email: 343618924@qq.com
 * @Desc:
 */
@RestController
public class RabbitmqTestController {
    @Autowired
    private TestProducer testProducer;

    @GetMapping("/send")
    public void send() {
        Message message = new Message();
        message.setOrderId("aaaa");
        testProducer.sendMsg(message);
    }
}

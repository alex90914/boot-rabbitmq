package com.dream.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wubo
 * @Date: 2019-01-23
 * @Time: 13:18
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue(RabbitmqConstant.TEST_QUEUE);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitmqConstant.TEST_EXCHANGE);
    }

    @Bean
    public Binding bindingEmailExchangeMessage() {
        return BindingBuilder
                .bind(helloQueue())
                .to(exchange())
                .with(RabbitmqConstant.TEST_ROUTINGKEY);
    }
}

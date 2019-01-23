package com.dream.rabbitmq;

import lombok.Data;

/**
 * @author: wubo
 * @Date: 2019-01-23
 * @Time: 13:50
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Data
public class Message {
    private String orderId;
    private String message;
}

package com.fobgochod.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 订单消息发送
 *
 * @author seven
 * @date 2019/5/26
 */
@Component
@EnableBinding(OrderBinder.class)
public class OrderMessageProvider {

    @Autowired
    @Output(OrderBinder.OUTPUT_ORDER)
    private MessageChannel channel;

    /**
     * 发送消息
     */
    public void send(String json) {
        Message message = MessageBuilder.withPayload(json).build();
        channel.send(message);
    }
}

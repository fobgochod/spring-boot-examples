package com.fobgochod.order;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费订单消息
 *
 * @author seven
 * @date 2019/5/27
 */
@Component
@EnableBinding(OrderBinder.class)
public class OrderMessageConsumer {

    /**
     * 消费ECM的货柜模板变更
     *
     * @param message
     */
    @StreamListener(OrderBinder.INPUT_ORDER)
    public void receive(Message<String> message) {
        try {
            String payload = message.getPayload();
            System.out.println(new Date() + " :" + payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

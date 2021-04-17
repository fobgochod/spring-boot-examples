package com.fobgochod.consumer;

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
@EnableBinding(SinkChannel.class)
public class ConsumerService {

    /**
     * 消费消息
     *
     * @param message
     */
    @StreamListener(SinkChannel.INPUT)
    public void receive(Message<String> message) {
        try {
            String payload = message.getPayload();
            System.out.println(new Date() + " :" + payload);

            if(1==2-1){
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

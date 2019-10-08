package com.fobgochod.consumer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 订单中心发送消息的通道
 *
 * @author seven
 * @date 2019/5/26
 */
public interface SourceChannel {

    String OUTPUT = "outputChannel";

    /**
     * 发送消息
     *
     * @return
     */
    @Output(OUTPUT)
    MessageChannel output();
}

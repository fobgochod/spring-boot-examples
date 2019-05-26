package com.fobgochod.order;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 订单中心发送消息的通道
 *
 * @author seven
 * @date 2019/5/26
 */
public interface OrderBinder {

    String OUTPUT_ORDER = "orderMessageChannel";

    /**
     * 发送消息
     *
     * @return
     */
    @Output(OUTPUT_ORDER)
    MessageChannel messageOutput();
}

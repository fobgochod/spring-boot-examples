package com.fobgochod.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface OrderBinder {

    String INPUT_ORDER = "orderMessageChannel";

    @Input(INPUT_ORDER)
    MessageChannel messageInput();
}

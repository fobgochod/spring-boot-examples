package com.fobgochod.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SinkChannel {

    String INPUT = "inputChannel";

    @Input(INPUT)
    SubscribableChannel input();
}

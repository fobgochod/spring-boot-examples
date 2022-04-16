package com.fobgocod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dubbo Registry ZooKeeper Consumer Bootstrap
 */
@SpringBootApplication
public class ApacheDubboConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ApacheDubboConsumer.class, args);
    }
}

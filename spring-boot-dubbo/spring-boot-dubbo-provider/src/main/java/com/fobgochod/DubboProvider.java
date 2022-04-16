package com.fobgochod;

import com.fobgochod.service.DefaultDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dubbo Registry ZooKeeper Provider Bootstrap
 *
 * @see DefaultDemoService
 * @since 2.7.0
 */
@SpringBootApplication
public class DubboProvider {

    public static void main(String[] args) {
        SpringApplication.run(DubboProvider.class, args);
    }
}

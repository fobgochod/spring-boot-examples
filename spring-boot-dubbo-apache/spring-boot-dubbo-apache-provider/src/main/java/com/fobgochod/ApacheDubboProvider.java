package com.fobgochod;

import com.fobgochod.sample.provider.DefaultDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dubbo Registry ZooKeeper Provider Bootstrap
 *
 * @see DefaultDemoService
 * @since 2.7.0
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.fobgochod.sample.provider"})
public class ApacheDubboProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApacheDubboProvider.class, args);
    }
}

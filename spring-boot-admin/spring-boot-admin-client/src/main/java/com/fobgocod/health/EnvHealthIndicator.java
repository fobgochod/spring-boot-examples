package com.fobgocod.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @auther: Xiao
 * @date: 2019/5/21 22:40
 * @description: 功能描述
 */
@Component
public class EnvHealthIndicator extends AbstractHealthIndicator {

    private static final String env = "http://localhost:8081/api/env";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        doEnvHealthCheck(builder);
    }

    private void doEnvHealthCheck(Health.Builder builder) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> details = restTemplate.getForObject(env, Map.class);
            builder.up().withDetails(details);
        } catch (Exception e) {
            builder.down().withDetail("uri", env).withDetail("error", e.getMessage());
        }
    }
}

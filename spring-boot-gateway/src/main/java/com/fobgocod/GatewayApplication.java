package com.fobgocod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p ->
                        p.path("/xxoo")
                                .filters(f -> f.stripPrefix(1)).
                                uri("https://www.mashibing.com"))
                .route(p ->
                        p.path("/go/**")
                                .filters(f ->
                                        f.stripPrefix(1)
                                                .addRequestHeader("digi-middleware-auth-user", "1e613481-da02-4cd9-84ac-5dae091ce14b")
                                )
                                .uri("http://172.16.2.141:32511"))
                .build();
    }
}

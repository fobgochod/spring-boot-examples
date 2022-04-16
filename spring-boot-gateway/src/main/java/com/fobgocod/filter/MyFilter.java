package com.fobgocod.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 功能描述
 *
 * @author seven
 * @date 2021/5/25
 */
//@Component
public class MyFilter implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> token = request.getHeaders().get("token");
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //DataBuffer wrap = exchange.getResponse().bufferFactory().wrap("401没有权限".getBytes(StandardCharsets.UTF_8));
            //exchange.getResponse().writeWith(Mono.just(wrap));
            return exchange.getResponse().setComplete();
        }
        request.mutate()
                .header("digi-middleware-auth-user", "1e613481-da02-4cd9-84ac-5dae091ce14b")
                .build();
        System.out.println("add user token...");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

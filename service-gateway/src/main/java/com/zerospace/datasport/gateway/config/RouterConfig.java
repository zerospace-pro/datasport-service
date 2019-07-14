package com.zerospace.datasport.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 <p>
 路由反向代理
 </p>
 @author nathan
 @version 2019-07-13 */
@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**") //转发路径
                        .uri("lb:/service-user") // 从注册中心中获取服务
                        .id("user-service")
                ).build();
    }
}

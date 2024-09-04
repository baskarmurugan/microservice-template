package org.template.apigatewayservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.template.apigatewayservice.client.SecurityServiceClient;
import org.template.apigatewayservice.model.AuthResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SecurityFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    private final SecurityServiceClient securityServiceClient;

    @Autowired
    public SecurityFilter(@Lazy SecurityServiceClient securityServiceClient) {
        this.securityServiceClient = securityServiceClient;
    }

    private static final List<String> WHITE_LIST_URLS = List.of(
            "/api/user/login",
            "/api/user/signup"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Skip verification for white-listed URLs
        if (isWhiteListed(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.info("***UNAUTHORIZED***");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            AuthResponse authResponse = securityServiceClient.verifyToken(token,path);
            if (authResponse.isAuthenticated()){
                return chain.filter(exchange);
            }else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }
        }catch (Exception e){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


    }

    private boolean isWhiteListed(String path) {
        return WHITE_LIST_URLS.stream().anyMatch(path::startsWith);
    }

    @Override
    public int getOrder() {
        return -1; // Priority for this filter to be executed before routing
    }
}

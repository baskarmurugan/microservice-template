package org.template.apigatewayservice.client;

import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.template.apigatewayservice.config.FeignConfig;
import org.template.apigatewayservice.model.AuthResponse;

@FeignClient(name = "security-service", url = "${security-service.url}", configuration = FeignConfig.class)
public interface SecurityServiceClient {

    @GetMapping("/api/auth/verifyAuthorization")
    AuthResponse verifyToken(@RequestHeader("Authorization") String token, @RequestParam("path")String path);


}

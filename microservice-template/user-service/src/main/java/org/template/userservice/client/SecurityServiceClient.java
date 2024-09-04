package org.template.userservice.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.template.userservice.config.FeignConfig;
import org.template.userservice.model.JwtAuthenticationResponse;
import org.template.userservice.model.LoginRequest;
@FeignClient(name = "security-service", url = "${security-service.url}", configuration = FeignConfig.class)
public interface SecurityServiceClient {
    @PostMapping("/api/auth/login")
    @Consumes(MediaType.APPLICATION_JSON)
    JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest);
    @GetMapping("/api/auth/test")
    String test();
}

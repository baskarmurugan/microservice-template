package org.template.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.template.orderservice.config.FeignConfig;
import org.template.orderservice.model.userservice.UserDetails;

@FeignClient(name = "USER-SERVICE", configuration = FeignConfig.class)

public interface UserServiceClient {
    @GetMapping("/api/user/{id}")
    UserDetails getUser(@PathVariable("id") Long id);
}

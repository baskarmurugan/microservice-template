package org.template.apigatewayservice.model;

import lombok.Data;

@Data
public class AuthResponse {
    private boolean authenticated;
    private String username; // Optional, if you need to pass user details


}

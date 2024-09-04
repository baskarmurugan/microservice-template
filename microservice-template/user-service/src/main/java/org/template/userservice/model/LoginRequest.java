package org.template.userservice.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

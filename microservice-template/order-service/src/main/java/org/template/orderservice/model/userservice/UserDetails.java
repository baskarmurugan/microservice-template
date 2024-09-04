package org.template.orderservice.model.userservice;

import lombok.Data;

@Data
public class UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;

}

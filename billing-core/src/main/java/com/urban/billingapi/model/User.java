package com.urban.billingapi.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}

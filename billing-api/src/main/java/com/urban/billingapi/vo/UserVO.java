package com.urban.billingapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String userToken;
}

package com.urban.billingapi.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.urban.billingapi.model.EntityExample;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "user_unique", columnNames = {"user_name", "first_name", "last_name", "email"}),
        @UniqueConstraint(name = "username_unique", columnNames = {"user_name"}),
        @UniqueConstraint(name = "email_unique", columnNames = {"email"})
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User implements EntityExample<User, Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name", columnDefinition = "VARCHAR(256)")
    private String userName;
    @Column(name = "first_name", columnDefinition = "VARCHAR(256)")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "VARCHAR(256)")
    private String lastName;
    @Column(name = "email", columnDefinition = "VARCHAR(256)")
    private String email;
    @Column(name = "user_token", columnDefinition = "VARCHAR(256)")
    private String userToken;
}

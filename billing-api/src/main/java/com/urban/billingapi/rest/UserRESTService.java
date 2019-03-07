package com.urban.billingapi.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.service.UserService;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/user")
@Api
public class UserRESTService implements BillingRESTService<User, Long> {
    private final UserService userService;
    private final IUserRepository repository;

    @Override
    public User createNew(@RequestBody User user) {
        return userService.createClient(user);
    }

}

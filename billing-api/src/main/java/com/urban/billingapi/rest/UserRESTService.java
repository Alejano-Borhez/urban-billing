package com.urban.billingapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.model.user.User;
import com.urban.billingapi.service.UserService;
import com.urban.billingapi.vo.UserVO;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/user")
@Api
public class UserRESTService extends AbstractRestService<Long, User, UserVO> {
    private final UserService service;
}

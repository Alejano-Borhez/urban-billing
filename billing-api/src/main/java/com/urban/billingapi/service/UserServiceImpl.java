package com.urban.billingapi.service;

import org.springframework.stereotype.Component;

import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.service.mapper.UserVOMapper;
import com.urban.billingapi.vo.UserVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Getter
public class UserServiceImpl extends CrudService<Long, User, UserVO> {
    private final IUserRepository repository;
    private final UserVOMapper mapper;

}

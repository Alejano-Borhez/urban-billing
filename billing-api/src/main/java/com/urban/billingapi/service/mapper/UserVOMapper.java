package com.urban.billingapi.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.urban.billingapi.model.user.User;
import com.urban.billingapi.vo.UserVO;

@Component
public class UserVOMapper implements EntityToVOMapper<Long, User, UserVO> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserVO toVO(User entity) {
        return modelMapper.map(entity, UserVO.class);
    }

    @Override
    public User toEntity(UserVO vo) {
        return modelMapper.map(vo, User.class);
    }
}

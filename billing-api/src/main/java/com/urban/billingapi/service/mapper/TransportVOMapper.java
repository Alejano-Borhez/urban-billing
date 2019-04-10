package com.urban.billingapi.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.vo.TransportVO;

@Component
public class TransportVOMapper implements EntityToVOMapper<Long, Transport, TransportVO>

{
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public TransportVO toVO(Transport entity) {
        return modelMapper.map(entity, TransportVO.class);
    }

    @Override
    public Transport toEntity(TransportVO vo) {
        return modelMapper.map(vo, Transport.class);
    }
}

package com.urban.billingapi.service.mapper;

import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.vo.CityVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * The simplest implementation
 */

@Component
public class CityVOMapper implements EntityToVOMapper<Long, City, CityVO>

{
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public CityVO toVO( City entity )
    {
        return modelMapper.map( entity, CityVO.class );
    }

    @Override
    public City toEntity( CityVO vo )
    {
        return modelMapper.map( vo, City.class );
    }
}

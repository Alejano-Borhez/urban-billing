package com.urban.billingapi.service;

import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.service.mapper.EntityToVOMapper;
import com.urban.billingapi.service.mapper.TicketVOMapper;
import com.urban.billingapi.service.mapper.VendorVOMapper;
import com.urban.billingapi.vo.CityVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl extends CrudService<Long,City,CityVO>
{
    @Getter
    private final EntityToVOMapper<Long, City,CityVO> mapper;
    @Getter
    private final ICityRepository repository;

}

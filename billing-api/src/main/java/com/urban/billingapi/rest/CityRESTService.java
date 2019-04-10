package com.urban.billingapi.rest;

import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.service.CityServiceImpl;
import com.urban.billingapi.vo.CityVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping( "/api/city" )
public class CityRESTService extends AbstractRestService<Long, City, CityVO>
{
    private final CityServiceImpl service;
}

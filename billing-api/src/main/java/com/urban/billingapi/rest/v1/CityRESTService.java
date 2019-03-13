package com.urban.billingapi.rest.v1;

import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.rest.BillingRESTService;
import com.urban.billingapi.service.v1.CityServiceImpl;
import com.urban.billingapi.service.v1.CrudService;
import com.urban.billingapi.service.v1.mapper.CityVOMapper;
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

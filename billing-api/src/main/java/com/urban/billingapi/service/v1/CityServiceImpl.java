package com.urban.billingapi.service.v1;

import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.v1.mapper.EntityToVOMapper;
import com.urban.billingapi.service.v1.mapper.TicketVOMapper;
import com.urban.billingapi.service.v1.mapper.VendorVOMapper;
import com.urban.billingapi.vo.CityVO;
import com.urban.billingapi.vo.VendorVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl extends CrudService<Long,City,CityVO>
{
    @Getter
    private final EntityToVOMapper<City,CityVO> mapper;
    @Getter
    private final ICityRepository repository;

}

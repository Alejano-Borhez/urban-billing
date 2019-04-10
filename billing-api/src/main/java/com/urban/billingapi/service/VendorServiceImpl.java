package com.urban.billingapi.service;

import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.mapper.VendorVOMapper;
import com.urban.billingapi.vo.VendorVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl extends CrudService<Long,Vendor,VendorVO>
{
    @Getter
    private final VendorVOMapper mapper;
    @Getter
    private final IVendorRepository repository;
}

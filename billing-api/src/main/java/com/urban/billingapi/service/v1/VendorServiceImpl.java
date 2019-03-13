package com.urban.billingapi.service.v1;

import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.v1.mapper.VendorVOMapper;
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
public class VendorServiceImpl extends CrudService<Long,Vendor,VendorVO>
{
    @Getter
    private final VendorVOMapper mapper;
    @Getter
    private final IVendorRepository repository;
}

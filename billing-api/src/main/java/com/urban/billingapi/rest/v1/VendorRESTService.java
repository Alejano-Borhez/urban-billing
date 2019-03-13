package com.urban.billingapi.rest.v1;

import com.urban.billingapi.service.v1.VendorServiceImpl;
import com.urban.billingapi.vo.VendorVO;
import org.springframework.web.bind.annotation.*;
import com.urban.billingapi.model.vendor.Vendor;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping( "/api/vendor" )
@Api
public class VendorRESTService extends AbstractRestService<Long, Vendor, VendorVO>
{
    @Getter
    private final VendorServiceImpl service;
}

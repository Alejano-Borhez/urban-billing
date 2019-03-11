package com.urban.billingapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.VendorService;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/vendor")
@Api
public class VendorRESTService implements BillingRESTService<Vendor, Long> {
    private final IVendorRepository repository;
    private final VendorService vendorService;

    @Override
    public Vendor createNew(Vendor vendor) {
        return vendorService.createNew(vendor);
    }

}

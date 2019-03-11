package com.urban.billingapi.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.model.vendor.Vendor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class VendorService implements IBillingService<Vendor, Long> {
    @Getter
    private final IVendorRepository repository;

    @Override
    public void validate(Vendor vendor, boolean isNew) {
        IBillingService.super.validate(vendor, isNew);
        if (isNew) {
            if (StringUtils.isBlank(vendor.getName())) throw new UrbanServiceException("Vendor name is null");
            if (vendor.getCity() == null) throw new UrbanServiceException("City for new Vendor is null");
            if (vendor.getCurrency() == null) throw new UrbanServiceException("Currency for new Vendor is null");
        }
    }

}

package com.urban.billingapi.rest;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.VendorService;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/transport")
public class TransportRESTService implements BillingRESTService<Transport, Long> {
    private final RepositoryEntityLinks repositoryEntityLinks;
    private final ITransportRepository repository;

    @Override
    public Class<Transport> getEntityClass() {
        return Transport.class;
    }
}

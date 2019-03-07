package com.urban.billingapi.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Transport;
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

    @PostMapping("add/transport")
    public Vendor addSupportedTransport(@RequestParam Long vendorId, @RequestBody Transport transport) {
        return vendorService.addSupportedTransport(vendorId, transport);
    }

    @Override
    public Vendor createNew(Vendor entity) {
        return vendorService.createVendor(entity);
    }

    @GetMapping("findByTransportTypes")
    public Page<Vendor> findByTransportTypes(@RequestParam List<TransportType> transportTypes, @RequestParam int page, @RequestParam int size) {
        return vendorService.findByTransportTypes(transportTypes, PageRequest.of(page, size));
    }

}

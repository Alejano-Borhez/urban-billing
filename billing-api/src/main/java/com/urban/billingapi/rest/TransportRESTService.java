package com.urban.billingapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.model.vendor.Transport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/transport")
public class TransportRESTService implements BillingRESTService<Transport, Long> {
    private final ITransportRepository repository;

}

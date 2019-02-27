package com.urban.billingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;

@Service
public class BillingService {
    @Autowired
    private BraintreeGateway gateway;

}

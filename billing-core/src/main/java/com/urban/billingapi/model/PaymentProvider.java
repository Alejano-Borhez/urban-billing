package com.urban.billingapi.model;

import lombok.Data;

@Data
public class PaymentProvider {
    private Long id;
    private String name;
    private String providerBaseUrl;
    private String providerToken;
}

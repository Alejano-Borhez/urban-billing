package com.urban.billingapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaymentProvider {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String providerBaseUrl;
    private String providerToken;
}

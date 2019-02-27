package com.urban.billingapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaymentHolder {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long paymentProviderId;
}

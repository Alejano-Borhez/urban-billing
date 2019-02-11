package com.urban.billingapi.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.urban.billingapi.model.enums.PaymentType;

import lombok.Data;

@Data
public class PaymentSource {
    @Id
    private Long id;
    private Long userId;
    private Long cardProviderId;
    @Enumerated(STRING)
    private PaymentType paymentType;
    private String providerToken;
}

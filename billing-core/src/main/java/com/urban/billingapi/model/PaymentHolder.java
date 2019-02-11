package com.urban.billingapi.model;

import lombok.Data;

@Data
public class PaymentHolder {
    private Long id;
    private Long userId;
    private Long cardProviderId;
}

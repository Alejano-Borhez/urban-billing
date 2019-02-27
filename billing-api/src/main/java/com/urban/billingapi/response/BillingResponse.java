package com.urban.billingapi.response;

import org.springframework.hateoas.Links;

import lombok.Data;

@Data
public class BillingResponse<T> {
    private T entity;
    private Links entityLinks;
}

package com.urban.billingapi.model.exceptions;

public class UrbanServiceException extends RuntimeException {
    public UrbanServiceException(String message) {
        super(message);
    }
}

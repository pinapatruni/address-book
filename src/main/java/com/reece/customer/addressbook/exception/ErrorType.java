package com.reece.customer.addressbook.exception;

public enum ErrorType {

    GENERIC_SYSTEM_ERROR("ERR_001","System Error"),
    ERROR_RESOURCE_NOT_FOUND("ERR_002","Requested resource not found"),
    ERROR_VALIDATION("ERR_003", "The request had validation errors");

    private final String code;

    private final String description;

    ErrorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}

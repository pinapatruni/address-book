package com.reece.customer.addressbook.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String s) {
        super(s);
    }
    public ServiceException(String s, Exception e) {
        super(s, e);
    }
}

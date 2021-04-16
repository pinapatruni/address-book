package com.reece.customer.addressbook.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressBookList {

    private List<Long> list;

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }
}

package com.reece.customer.addressbook.controller;

import com.reece.customer.addressbook.AddressBookApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = AddressBookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootSpecification {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;
}

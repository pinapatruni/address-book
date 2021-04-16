package com.reece.customer.addressbook.controller;

import com.reece.customer.addressbook.model.AddressBook;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookControllerIntegrationTest extends SpringBootSpecification {

    @Test
    public void testCreateAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBook.setName("adbook1");
        ResponseEntity<AddressBook> addressBookEntity = this.restTemplate.postForEntity("http://localhost:" + this.port + "/addressBook", addressBook, AddressBook.class);

        assertEquals(201, addressBookEntity.getStatusCodeValue());
        assertEquals("adbook1", addressBookEntity.getBody().getName());
    }

    @Test
    public void testGetAddressBookList() {
        testCreateAddressBook();
        ResponseEntity<List<AddressBook>> addressBookListEntity = this.restTemplate.exchange("http://localhost:" + port + "/addressBook",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertEquals(200, addressBookListEntity.getStatusCodeValue());
        assertEquals("adbook1", addressBookListEntity.getBody().get(0).getName());
    }
}

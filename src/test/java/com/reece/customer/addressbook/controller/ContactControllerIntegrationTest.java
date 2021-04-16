package com.reece.customer.addressbook.controller;


import com.reece.customer.addressbook.model.AddressBook;
import com.reece.customer.addressbook.model.AddressBookList;
import com.reece.customer.addressbook.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactControllerIntegrationTest extends SpringBootSpecification{

    private final String test_contact_name = "xyz";
    private final String test_contact_phone = "0422222201";
    private final String test_addressbook_name = "adBook";
    private final String local_host = "http://localhost:";
    private final String addressbook_path = "/addressBook/";
    private final String contacts_path = "/contacts";

    private AddressBook createAddressBook(String name){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        ResponseEntity<AddressBook> addressBookEntity= this.restTemplate.postForEntity(local_host + this.port + addressbook_path, addressBook, AddressBook.class);
        return addressBookEntity.getBody();
    }

    private Contact addContact(String name, String phoneNumber, Long addressBookId) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);

        return this.restTemplate.postForEntity(
                local_host + this.port + addressbook_path +addressBookId+ contacts_path,
                contact,
                Contact.class).getBody();
    }

    private ResponseEntity<List<Contact>> getContactsByAddressBookId(Long addressBookId) {
        return this.restTemplate.exchange(
                local_host + this.port + addressbook_path + addressBookId + contacts_path,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

    @Test
    public void addContact(){
        AddressBook addressBook = createAddressBook(test_addressbook_name);
        Contact contact = addContact(test_contact_name, test_contact_phone, addressBook.getId());

        assertEquals(test_contact_name, contact.getName());
    }

    @Test
    public void getAllContactsByAddressBookId(){
        // returns addressBook created in the previous test because of same name
        AddressBook addressBook = createAddressBook(test_addressbook_name);

        ResponseEntity<List<Contact>> contactListEntity = getContactsByAddressBookId(addressBook.getId());

        assertEquals(200, contactListEntity.getStatusCodeValue());
        assertEquals(1, contactListEntity.getBody().size());
    }

    @Test
    public void deleteContactByContactId(){
        // returns addressBook created in the previous test because of same name
        AddressBook addressBook = createAddressBook(test_addressbook_name);

        // returns contact created in the previous test because of same name and phoneNumber
        Contact contact = addContact(test_contact_name, test_contact_phone, addressBook.getId());

        this.restTemplate.exchange(
                local_host + this.port + "/addressBook/contacts/"+contact.getId(),
                HttpMethod.DELETE,
                null,
                Object.class);

        ResponseEntity<List<Contact>> contactListEntity = getContactsByAddressBookId(addressBook.getId());

        assertEquals(200, contactListEntity.getStatusCodeValue());
        assertEquals(0, contactListEntity.getBody().size());
    }

    @Test
    public void getAllUniqueContactsByAddressIdList(){
        AddressBook addressBook = createAddressBook(test_addressbook_name);
        addContact(test_contact_name, test_contact_phone, addressBook.getId());
        AddressBook addressBook2 = createAddressBook("adBook2");
        addContact(test_contact_name, test_contact_phone, addressBook2.getId());

        AddressBookList addressBookList = new AddressBookList();
        addressBookList.setList(Arrays.asList(addressBook.getId(), addressBook2.getId()));
        HttpEntity<AddressBookList> requestEntity = new HttpEntity<>(addressBookList);
        
        ResponseEntity<List<Contact>> contactListEntity = this.restTemplate.exchange(
                local_host + this.port + "/addressBook/multiple/contacts",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(200, contactListEntity.getStatusCodeValue());
        //As name and phoneNumber are same for both the contacts, it returns only one contact
        assertEquals(1, contactListEntity.getBody().size());
        assertEquals(test_contact_name, contactListEntity.getBody().get(0).getName());
    }




}

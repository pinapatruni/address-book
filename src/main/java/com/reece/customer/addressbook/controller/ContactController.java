package com.reece.customer.addressbook.controller;

import com.reece.customer.addressbook.model.AddressBookList;
import com.reece.customer.addressbook.model.Contact;
import com.reece.customer.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/addressBook/{addressBookId}/contacts",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<Contact> addContact(@PathVariable(value = "addressBookId") Long addressBookId,
                                              @Valid @RequestBody Contact contact) {

        Contact contactResponse = contactService.addContact(contact, addressBookId);

        return new ResponseEntity<>(contactResponse, HttpStatus.CREATED);
    }


    @GetMapping(value = "/addressBook/{addressBookId}/contacts",
            produces = {"application/json"})
    public ResponseEntity<List<Contact>> getAllContactsByAddressBookId(@PathVariable (value = "addressBookId") Long addressBookId) {

        List<Contact> contactList = contactService.getAllContactsByAddressBookId(addressBookId);

        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/addressBook/contacts/{contactId}",
            produces = {"application/json"})
    public ResponseEntity<Object> deleteContactByContactId(@PathVariable (value = "contactId") Long contactId) {
        contactService.deleteContactByContactId(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addressBook/multiple/contacts",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<Contact>> getAllContactsByAddressIdList(@RequestBody AddressBookList addressBookList) {

        List<Contact> contactList = contactService.getAllContactsByAddressIdList(addressBookList);

        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

}

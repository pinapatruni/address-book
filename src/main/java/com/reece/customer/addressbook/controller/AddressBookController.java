package com.reece.customer.addressbook.controller;

import com.reece.customer.addressbook.model.AddressBook;
import com.reece.customer.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<AddressBook>> getAllAddressBooks() {

        List<AddressBook> addressBookList = addressBookService.getAllAddressBooks();

        return new ResponseEntity<>(addressBookList, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
                consumes = {"application/json"})
    public ResponseEntity<AddressBook> createAddressBook(@Valid @RequestBody AddressBook addressBook) {

        AddressBook addressBookResponse = addressBookService.createAddressBook(addressBook);

        return new ResponseEntity<>(addressBookResponse, HttpStatus.CREATED);
    }
}

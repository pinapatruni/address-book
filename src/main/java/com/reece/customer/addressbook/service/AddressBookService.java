package com.reece.customer.addressbook.service;

import com.reece.customer.addressbook.model.AddressBook;

import java.util.List;

public interface AddressBookService {

    List<AddressBook> getAllAddressBooks();

    AddressBook createAddressBook(AddressBook addressBook);
}

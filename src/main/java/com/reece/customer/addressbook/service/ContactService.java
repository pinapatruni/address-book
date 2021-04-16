package com.reece.customer.addressbook.service;

import com.reece.customer.addressbook.model.AddressBookList;
import com.reece.customer.addressbook.model.Contact;

import java.util.List;

public interface ContactService {

    Contact addContact(Contact contact, Long addressBookId);

    List<Contact> getAllContactsByAddressBookId(Long addressBookId);

    List<Contact> getAllContactsByAddressIdList(AddressBookList addressBookList);

    void deleteContactByContactId(Long contactId);
}

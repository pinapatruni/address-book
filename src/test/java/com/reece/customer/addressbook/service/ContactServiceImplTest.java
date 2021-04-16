package com.reece.customer.addressbook.service;

import com.reece.customer.addressbook.exception.ResourceNotFoundException;
import com.reece.customer.addressbook.exception.ServiceException;
import com.reece.customer.addressbook.model.AddressBook;
import com.reece.customer.addressbook.model.Contact;
import com.reece.customer.addressbook.repository.AddressBookRepository;
import com.reece.customer.addressbook.repository.ContactRepository;
import com.reece.customer.addressbook.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    private static final Long test_addressbook_id = 1L;

    private static final String test_addressbook_name = "adBook";

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private AddressBookRepository addressBookRepository;

    @Mock
    private ContactRepository contactRepository;

    private static AddressBook addressBook;
    private static Contact contact;

    static {
        addressBook = new AddressBook();
        addressBook.setName(test_addressbook_name);
        contact = new Contact();
        contact.setName("c1");
        contact.setPhoneNumber("001");
    }

    @Test
    public void addContact(){
        Optional<AddressBook> optionalAddressBook = Optional.of(addressBook);

        when(contactRepository.findFirstByAddressBookIdAndNameAndPhoneNumber(anyLong(), anyString(), anyString())).thenReturn(null);
        when(addressBookRepository.findById(anyLong())).thenReturn(optionalAddressBook);
        when(contactRepository.save(any())).thenReturn(contact);

        Contact response = contactService.addContact(contact, test_addressbook_id);

        assertEquals("c1", response.getName());
    }

    @Test
    public void addContact_no_addressBook(){
        Optional<AddressBook> optionalAddressBook = Optional.empty();

        when(contactRepository.findFirstByAddressBookIdAndNameAndPhoneNumber(anyLong(), anyString(), anyString())).thenReturn(null);
        when(addressBookRepository.findById(anyLong())).thenReturn(optionalAddressBook);

        assertThrows(ResourceNotFoundException.class, () -> contactService.addContact(contact, test_addressbook_id));
    }

    @Test
    public void addContact_existing_contact(){
        Contact contact1 = new Contact();
        contact1.setName("c2");
        contact1.setPhoneNumber("002");

        when(contactRepository.findFirstByAddressBookIdAndNameAndPhoneNumber(anyLong(), anyString(), anyString())).thenReturn(contact1);

        Contact response = contactService.addContact(contact1, test_addressbook_id);

        assertEquals("c2", response.getName());
        assertEquals("002", response.getPhoneNumber());
    }

    @Test
    public void addContact_exception(){

        when(contactRepository.findFirstByAddressBookIdAndNameAndPhoneNumber(anyLong(), anyString(), anyString())).thenThrow(new RuntimeException());

        assertThrows(ServiceException.class, () -> contactService.addContact(contact, test_addressbook_id));
    }

    /*
    Due to time constraint, I was not able to cover unit test cases for below 3 methods.

    * getAllContactsByAddressBookId
    * getAllContactsByAddressIdList
    * deleteContactByContactId

    */
}

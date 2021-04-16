package com.reece.customer.addressbook.service;

import com.reece.customer.addressbook.exception.ServiceException;
import com.reece.customer.addressbook.model.AddressBook;
import com.reece.customer.addressbook.repository.AddressBookRepository;
import com.reece.customer.addressbook.service.impl.AddressBookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceImplTest {

    private static String test_addressbook_name = "adBook";

    @InjectMocks
    private AddressBookServiceImpl addressBookService;

    @Mock
    private AddressBookRepository addressBookRepository;

    private static List<AddressBook> addressBookList;
    private static AddressBook addressBook;

    static {
        addressBookList = new ArrayList<>();
        addressBook = new AddressBook();
        addressBook.setName(test_addressbook_name);
        addressBookList.add(addressBook);
    }

    @Test
    public void getAllAddressBooks(){

        when(addressBookRepository.findAll()).thenReturn(addressBookList);

        List<AddressBook> list = addressBookService.getAllAddressBooks();

        assertEquals(1, list.size());
        assertEquals(test_addressbook_name, list.get(0).getName());
    }

    @Test
    public void createAddressBook(){

        when(addressBookRepository.findByName(test_addressbook_name)).thenReturn(new ArrayList<>());
        when(addressBookRepository.save(any())).thenReturn(addressBook);

        AddressBook response = addressBookService.createAddressBook(addressBook);

        assertEquals(test_addressbook_name, response.getName());
    }

    @Test
    public void createAddressBook_existingRecord(){
        String adBookExisting = "adBookExisting";
        List<AddressBook> addressBookList1 = new ArrayList<>();
        AddressBook addressBook1 = new AddressBook();
        addressBook1.setName(adBookExisting);
        addressBookList1.add(addressBook1);

        when(addressBookRepository.findByName(adBookExisting)).thenReturn(addressBookList1);

        AddressBook response = addressBookService.createAddressBook(addressBook1);

        assertEquals(adBookExisting, response.getName());
    }

    @Test
    public void createAddressBook_exception(){

        when(addressBookRepository.findByName(test_addressbook_name)).thenThrow(new RuntimeException());

        assertThrows(ServiceException.class, () -> addressBookService.createAddressBook(addressBook));
    }
}

package com.reece.customer.addressbook.service.impl;

import com.reece.customer.addressbook.exception.ServiceException;
import com.reece.customer.addressbook.model.AddressBook;
import com.reece.customer.addressbook.repository.AddressBookRepository;
import com.reece.customer.addressbook.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    private static final Logger logger = LoggerFactory.getLogger(AddressBookServiceImpl.class);

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Override
    public List<AddressBook> getAllAddressBooks() {
        try {
            return addressBookRepository.findAll();
        } catch (Exception ex) {
            logger.error("Exception in getting addressBook list error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in getting addressBook list");
        }
    }

    @Override
    public AddressBook createAddressBook(AddressBook addressBook) {
        try {
            List<AddressBook> addressBookList = addressBookRepository.findByName(addressBook.getName());
            if (!addressBookList.isEmpty()) {
                return addressBookList.get(0);
            }
            return addressBookRepository.save(addressBook);
        }catch (Exception ex) {
            logger.error("Exception in creating a addressBook error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in creating addressBook");
        }
    }
}

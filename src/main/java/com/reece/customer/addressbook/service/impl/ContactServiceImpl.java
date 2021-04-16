package com.reece.customer.addressbook.service.impl;

import com.reece.customer.addressbook.exception.ResourceNotFoundException;
import com.reece.customer.addressbook.exception.ServiceException;
import com.reece.customer.addressbook.model.AddressBookList;
import com.reece.customer.addressbook.model.Contact;
import com.reece.customer.addressbook.repository.AddressBookRepository;
import com.reece.customer.addressbook.repository.ContactRepository;
import com.reece.customer.addressbook.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;


    @Override
    public Contact addContact(Contact contact, Long addressBookId) {
        try {
            Contact existingContact = contactRepository.findFirstByAddressBookIdAndNameAndPhoneNumber(addressBookId, contact.getName(), contact.getPhoneNumber());
            if (existingContact != null){
                return existingContact;
            }
            return addressBookRepository.findById(addressBookId).map(addressBook -> {
                contact.setAddressBook(addressBook);
                return contactRepository.save(contact);
            }).orElseThrow(() -> new ResourceNotFoundException("AddressBookId: " + addressBookId + " not found"));
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("Exception in adding contact to addressBook error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in adding contact to addressBook");
        }
    }

    @Override
    public List<Contact> getAllContactsByAddressBookId(Long addressBookId) {
        try {
            return contactRepository.findByAddressBookId(addressBookId);
        } catch (Exception ex) {
            logger.error("Exception in getting contacts from addressBook error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in getting contacts from addressBook");
        }
    }

    @Override
    public List<Contact> getAllContactsByAddressIdList(AddressBookList addressBookList) {
        try {
            List<Contact> contactList = contactRepository.findByAddressBookIdIn(addressBookList.getList());
            return contactList.stream().filter(distinctByKeys(Contact::getName, Contact::getPhoneNumber)).collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Exception in getting contacts from addressBooks error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in getting contacts from addressBooks");
        }
    }

    @Override
    public void deleteContactByContactId(Long contactId) {
        try {
            contactRepository.findById(contactId).map(contact -> {
                contactRepository.delete(contact);
                return contact;
            }).orElseThrow(() -> new ResourceNotFoundException("ContactId: " + contactId + " not found"));

        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("Exception in deleting a contact error={}", ex.getMessage(), ex);
            throw new ServiceException("System encountered error in deleting a contact");
        }
    }

    private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }
}

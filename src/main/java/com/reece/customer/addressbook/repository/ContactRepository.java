package com.reece.customer.addressbook.repository;

import com.reece.customer.addressbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByAddressBookId(Long addressBookId);

    Contact findFirstByAddressBookIdAndNameAndPhoneNumber(Long addressBookId, String name, String phoneNumber);

    List<Contact> findByAddressBookIdIn(List<Long> addressBookIds);
}

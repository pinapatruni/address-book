package com.reece.customer.addressbook.repository;

import com.reece.customer.addressbook.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    List<AddressBook> findByName(String name);
}

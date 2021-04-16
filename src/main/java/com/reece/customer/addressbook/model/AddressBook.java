package com.reece.customer.addressbook.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="address_book")
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotEmpty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by chepiv on 23/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Entity
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String firstName;
    @Column(length = 100)
    private String secondName;
    @Column(nullable = false,length = 100)
    private String surname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

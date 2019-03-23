package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by chepiv on 21/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Entity
@Getter
@Setter
public class Address {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String country;
    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String street;
    @Column(length = 10)
    private String houseNumber;
    @Column(length = 10)
    private String apartmentNumber;
    @Column(length = 6)
    private String zipCode;
    @Column(length = 50)
    private String state;
}

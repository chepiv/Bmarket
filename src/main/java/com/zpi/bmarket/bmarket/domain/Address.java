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
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column
    private String street;
    @Column
    private String houseNumber;
    @Column
    private String apartmentNumber;
    @Column
    private String zipCode;
    @Column
    private String state;
}

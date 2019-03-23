package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chepiv on 18/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(nullable = false,unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private Date birthDate;
    @Column
    private String avatarUrl;
    @Column
    @ManyToOne
    private Address address;

}

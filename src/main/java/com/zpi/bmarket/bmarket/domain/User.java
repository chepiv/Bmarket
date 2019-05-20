package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Column(length = 30)
    private String name;
    @Column(length = 40)
    private String surname;
    @Column(nullable = false,unique = true,length = 30)
    private String login;
    @Column(nullable = false,length = 50)
    private String password;
    @Column(nullable = false,unique = true,length = 100)
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column
    private String avatarUrl;
    @ManyToOne
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Book> books;
    @OneToMany(mappedBy = "buyerUser")
    private List<Offer> offers;

}

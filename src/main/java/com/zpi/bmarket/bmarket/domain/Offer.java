package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chepiv on 25/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Entity
@Getter
@Setter
public class Offer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date publishDate;
    @Column
    private Date boughtDate;
    @ManyToOne
    @JoinColumn(name = "offer_type_id")
    private OfferType offerType;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @Column(length = 100)
    private String title;
    @Column(length = 2000)
    private String description;
    @Column(columnDefinition = "DECIMAL(6,2)")
    private int price;
    @Column(length = 100)
    private String city;
    @ManyToOne
    @JoinColumn(name = "buyerUser_id")
    private User buyerUser;

}

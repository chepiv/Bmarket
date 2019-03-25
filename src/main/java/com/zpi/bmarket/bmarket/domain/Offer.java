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
    private Long id;
    @Column
    private Date publishDate;
    @Column
    private Date boughtDate;
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(length = 100)
    private String title;
    @Column(length = 500)
    private String description;
    @Column(columnDefinition = "DECIMAL(19,0)")
    private int price;
    @Column(length = 100)
    private String city;
    @ManyToOne
    @JoinColumn(name = "buyerUser_id")
    private User buyerUser;

}

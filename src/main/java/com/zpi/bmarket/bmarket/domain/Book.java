package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by chepiv on 23/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Entity
@Getter
@Setter
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String authors;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "book_condition_id")
    private BookCondition bookCondition;

    @Column(length = 100)
    private String publisher;

    @Column(length = 20)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToMany
    @JoinTable(
            name = "exchangeOffers_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "exchangeOffer_id")
    )
    List<ExchangeOffer> exchangeOffers;


}

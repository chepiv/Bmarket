package com.zpi.bmarket.bmarket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "books")
    List<Author> authors;

    @Column(name = "book_condition",length = 50)
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(length = 100)
    private String publisher;

    @Column(length = 20)
    private String isbn;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "exchangeOffers_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "exchangeOffer_id")
    )
    List<ExchangeOffer> exchangeOffers;


}

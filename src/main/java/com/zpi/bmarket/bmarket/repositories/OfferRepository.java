package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.OfferType;
import com.zpi.bmarket.bmarket.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    Page<Offer> findAllByStatusAndOfferTypeInAndBooksInAndPriceBetween(Status status, List<OfferType> offerTypes, List<Book> books, int priceMin, int priceMax, Pageable pageable);
    Page<Offer> findAllByStatusAndOfferTypeInAndBooksInAndPriceBetweenAndTitleIsContaining(Status status, List<OfferType> offerTypes, List<Book> books, int priceMin, int priceMax,String textQuery, Pageable pageable);

    Offer findOfferById(Long id);
    Page<Offer> findDistinctByStatusAndOfferTypeInAndBooksInAndPriceBetween(Status status, List<OfferType> offerTypes, List<Book> books, int priceMin, int priceMax, Pageable pageable);

}

package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.OfferType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    Page<Offer> findAll(Pageable pageable);
    Page<Offer> findAllByDescriptionContaining(String description,Pageable pageable);
    Page<Offer> findAllByOfferType(OfferType type,Pageable pageable);
}

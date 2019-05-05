package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    Page<Offer> findAll(Pageable pageable);
}

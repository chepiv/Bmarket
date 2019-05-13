package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.BookCondition;
import org.springframework.data.repository.CrudRepository;

public interface BookConditionRepository extends CrudRepository<BookCondition,Long> {
    @Override
    Iterable<BookCondition> findAll();
}

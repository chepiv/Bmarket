package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {
    Status getFirstById(Long id);
}

package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}

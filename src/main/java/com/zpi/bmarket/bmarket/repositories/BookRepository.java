package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.Category;
import com.zpi.bmarket.bmarket.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chepiv on 08/04/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
public interface BookRepository extends CrudRepository<Book,Long> {
    List<Book> findAllByUserId(Long id);
    List<Book> findAllByBookConditionIn(List<BookCondition> bookCondition);
    List<Book> findAllByOffer(Offer offer);
    List<Book> findAllByCategory(Category category);
}

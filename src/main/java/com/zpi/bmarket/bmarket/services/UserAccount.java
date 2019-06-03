package com.zpi.bmarket.bmarket.services;

import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAccount {
    public static void removeBook(Book book){
        book.setOffer(null);
        book.setUser(null);
        //TODO: check if offer has any more books
    }

    public static void removeBooksFromOffer(Long offerId, OfferRepository offerRepository, BookRepository bookRepository){
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new IllegalArgumentException("id: " + offerId));
        removeBooksFromOffer(offer,offerRepository,bookRepository);
    }
    public static void removeBooksFromOffer(Offer offer, OfferRepository offerRepository, BookRepository bookRepository) {
        List<Book> books = bookRepository.findAllByOffer(offer);
        for (Book b :
                books) {
            b.setOffer(null);
        }
    }
}

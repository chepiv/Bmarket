package com.zpi.bmarket.bmarket.services;

import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public static PostStatus processBuyOffer(Offer offer, OfferRepository offerRepository, UserRepository userRepository, StatusRepository statusRepository){

        List<Book> books = offer.getBooks();
        User seller = books.get(0).getUser();
        User buyer = offer.getBuyerUser();
        try {
            long statusId = 2L;//Sprzedana
            offer.setStatus(statusRepository.findById(statusId).orElseThrow(() -> new IllegalArgumentException("id: " + statusId)));
            offer.setBoughtDate(new Date());
            offer.setBuyerUser(null);
            offerRepository.save(offer);

            for ( Book book : offer.getBooks()) {
                book.setUser(buyer);
                book.setOffer(null);
            }
            userRepository.save(buyer);
            userRepository.save(seller);
            return PostStatus.SUCCESS;

        } catch (Exception e) {
            return PostStatus.DATABASE_ERROR;
        }
    }
}

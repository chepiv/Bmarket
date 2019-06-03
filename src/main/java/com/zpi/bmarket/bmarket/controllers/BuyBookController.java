package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.Status;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BuyBookController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/buyBook/{offerId}")
    public String buyBook(HttpSession session, Model model, @PathVariable Long offerId) {

        Long id = ((Long) session.getAttribute("userId")).longValue();
        User buyer = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        //kupujący, oferta, sprzedający
        //wyświetlić dane oferty i sprzedającego
        //zmienić typ oferty i powiadomić sprzedającego o kupnie
        //kupujący ma info że kupił

        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new IllegalArgumentException("id: " + offerId));
        List<Book> books = offer.getBooks();
        User seller = books.get(0).getUser();

        model.addAttribute("seller", seller);
        model.addAttribute("offer", offer);
        model.addAttribute("buyer", buyer);
        return "buyBookView";
    }


    @RequestMapping(value = "/postBuyBook/{offerId}", method = RequestMethod.GET)
    public String postBuyBook(Model model, HttpSession session, @PathVariable Long offerId ) {

        PostStatus status = PostStatus.ERROR;

        //Long offerIdLong = Long.parseLong(offerId);
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User buyer = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new IllegalArgumentException("id: " + offerId));
        //User seller = userRepository.findByOffers(offer);
        List<Book> books = offer.getBooks();
        User seller = books.get(0).getUser();

        //pierwsza książka z oferty i z niej użytkownik (sprzedawca)

        try {
            long statusId = 2L;
            offer.setStatus(statusRepository.findById(statusId).orElseThrow(() -> new IllegalArgumentException("id: " + statusId)));
            offer.setBoughtDate(new Date());
            offerRepository.save(offer);

            for ( Book book : offer.getBooks()) {
                book.setUser(buyer);
            }
            userRepository.save(buyer);
            userRepository.save(seller);
            status = PostStatus.SUCCESS;

        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
        }

        model.addAttribute("status", status);

        return "postBuyBookView";
    }

}

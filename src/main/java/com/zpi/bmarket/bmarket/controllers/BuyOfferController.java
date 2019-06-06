package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.Status;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import com.zpi.bmarket.bmarket.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BuyOfferController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/buyOffer/{offerId}")
    public String buyBook(HttpSession session, Model model, @PathVariable Long offerId) {

        User buyer = UsersService.getUser(session,userRepository);

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
        return "buyOfferView";
    }


    @RequestMapping(value = "/postBuyOffer/{offerId}", method = RequestMethod.GET)
    public String postBuyBook(Model model, HttpSession session, @PathVariable Long offerId, RedirectAttributes ra) {

        PostStatus status = PostStatus.ERROR;

        User buyer = UsersService.getUser(session,userRepository);
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new IllegalArgumentException("id: " + offerId));

        Long statusID = 3L;//w trakcie
        Status statusInProces = statusRepository.findById(statusID).orElseThrow(()->new IllegalArgumentException("id: " + statusID));
        offer.setStatus(statusInProces);
        offer.setBuyerUser(buyer);
        status = PostStatus.SUCCESS;
//        status = UserAccount.processBuyOffer(offer,buyer,offerRepository,userRepository,statusRepository);

        model.addAttribute("status", status);

        //alert używając alertManager.js
        //trzeba w RedirectAttributes podać skąd przekierowanie i jaki status
        //dostępny na każdej podstronie
        //te 2 atrybuty wypełniają pola ukrytego div (w html/js.html), resztę robi skrypt
        //identycznie działają alerty przy logowaniu

        //TODO: test alerts
        //nie moge przetestowac bo wszystkie oferty są vhyba bez sprzedającego i dostaje errory na buyOffer

        ra.addFlashAttribute("redirectFrom", "postBuyOffer");
        ra.addFlashAttribute("status", status);

        return "redirect:/userAccount";

        //return "postBuyOfferView";
    }

}

package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.OfferDTO;
import com.zpi.bmarket.bmarket.DTO.UserDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class BuyBookController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRepository offerRepository;
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/buyBook")
    public String buyBook(HttpSession session, Model model) {

        Long id = ((Long) session.getAttribute("userId")).longValue();
        User buyer = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));


        //kupujący, oferta, sprzedający
        //wyświetlić dane oferty i sprzedającego
        //zmienić typ oferty i powiadomić sprzedającego o kupnie
        //kupujący ma info że kupił

        //Offer offer = offerRepository.findById();
        //User seller = userRepository.findByOffers(offer);

        //model.addAttribute("seller", seller);
        //model.addAttribute("offer", offer);
        //model.addAttribute("buyer", buyer);

        return "buyBookView";
    }


    @RequestMapping(value = "/postBuyBook", method = RequestMethod.POST)
    public String postBuyBook( Model model, HttpSession session, User buyer, User seller, Offer offer) {

        PostStatus status = PostStatus.ERROR;


        try {
            //offer.setStatus(statusRepository.findById(0L)); czy to wgoógle tutaj dawać
            //offer.setBoughtDate(new Date());
            //offerRepository.save(offer);
        }
        catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
        }

        model.addAttribute("status", status);

        return "postBuyBookView";
    }

}

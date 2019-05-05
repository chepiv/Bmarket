package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.AddOfferDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.OfferTypeRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class OfferController {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferTypeRepository offerTypeRepository;


    @GetMapping(value = "/addOffer")
    public String addOffer(WebRequest request, Model model, HttpSession session) {

        AddOfferDTO offerDTO = new AddOfferDTO();
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));


        model.addAttribute("offerDTO", offerDTO);
        model.addAttribute("offerTypes", offerTypeRepository.findAll());
        model.addAttribute("userBooks", user.getBooks());

        return "addOfferView";
    }


    @RequestMapping(value = "/postAddUserBook", method = RequestMethod.POST)
    public String postAddOffer(@ModelAttribute AddOfferDTO offerDTO, Model model, HttpSession session) {

        PostStatus status = PostStatus.ERROR;
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        Offer offer = offerDTO.getOffer(user);

        try {
            offerRepository.save(offer);
            model.addAttribute("offer", offer);
            status = PostStatus.SUCCESS;
        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
        }

        model.addAttribute("status", status);

        return "postAddOfferView";
    }
}

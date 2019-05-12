package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.OfferDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class OfferController {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferTypeRepository offerTypeRepository;
    @Autowired
    StatusRepository statusRepository;


    @GetMapping(value = "/addOffer")
    public String addOffer(WebRequest request, Model model, HttpSession session) {

        OfferDTO offerDTO = new OfferDTO();
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        PostStatus bookStatus = PostStatus.ERROR;

        model.addAttribute("offerDTO", offerDTO);
        model.addAttribute("offerTypes", offerTypeRepository.findAll());
        if (!user.getBooks().isEmpty()) {
            model.addAttribute("userBooks", user.getBooks());
            bookStatus = PostStatus.SUCCESS;
        }
        model.addAttribute("bookStatus", bookStatus);

        return "addOfferView";
    }


    @RequestMapping(value = "/postAddOffer", method = RequestMethod.POST)
    public String postAddOffer(@ModelAttribute OfferDTO offerDTO, Model model, HttpSession session) {

        PostStatus status = PostStatus.ERROR;
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        offerDTO.setStatus(statusRepository.findById(0L));  // TODO: jakie statusy - ustawić że oferta jest aktywna
        offerDTO.setPublishDate(new Date());

        Offer offer = offerDTO.createOffer(user);

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


    @GetMapping(value = "/editOffer")
    public String editOffer(Model model, HttpSession session, long id) {

        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid offer Id:" + id));

        Long userId = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));

        model.addAttribute("offer", offer);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("offerTypes", offerTypeRepository.findAll());

        return "editOfferView";
    }


    @RequestMapping(value = "/postEditOffer", method = RequestMethod.POST)
    public String postEditOffer( Model model, HttpSession session, Offer offer) {

        PostStatus status = PostStatus.ERROR;

        try {
            offerRepository.save(offer);
        }
        catch (Exception e) {
        status = PostStatus.DATABASE_ERROR;
    }

        model.addAttribute("status", status);

        return "postEditOfferView";
    }


    @GetMapping(value = "/deleteOffer")
    public String deleteOffer(Model model, HttpSession session, Long id) {

        offerRepository.deleteById(id);

        return "listOfferView";
    }



}

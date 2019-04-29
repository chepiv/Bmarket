package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OffersListController {

    @Autowired
    private OfferRepository offerRepository;

    private int limit = 3;

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model, @PathVariable("index") int index) {
        List<Offer> offers = offerRepository.findAll(PageRequest.of(index - 1, limit)).getContent();

        model.addAttribute("offers", offers);
        model.addAttribute("index",index);


        return "listOffersView";
    }
}

package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class OffersListController {
    @Autowired
    private OfferRepository offerRepository;
    private int recordsPerSite = 10;
    private int sitesCount;
    ArrayList<Offer> offers;

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        offers = Lists.newArrayList(offerRepository.findAll());
        sitesCount = offers.size() / recordsPerSite + 1;
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model, @PathVariable("index") int index) {
//        ArrayList<Offer> offers = Lists.newArrayList(offerRepository.findAll());

        model.addAttribute("offers", offers);

        return "offersView";
    }
}

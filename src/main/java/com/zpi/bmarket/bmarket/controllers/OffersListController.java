package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.OfferType;
import com.zpi.bmarket.bmarket.domain.Status;
import com.zpi.bmarket.bmarket.repositories.BookConditionRepository;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.OfferTypeRepository;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class OffersListController {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private BookConditionRepository bookConditionRepository;
    @Autowired
    private OfferTypeRepository offerTypeRepository;
    @Autowired
    private StatusRepository statusRepository;

    private int limit = 3;

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model, @PathVariable("index") int index) {
        List<Offer> offers = offerRepository.findAll(PageRequest.of(index - 1, limit)).getContent();
        List<BookCondition> conditions = Lists.newArrayList(bookConditionRepository.findAll());
        List<OfferType> types = Lists.newArrayList(offerTypeRepository.findAll());
        List<Status> statuses = Lists.newArrayList(statusRepository.findAll());

        model.addAttribute("offers", offers);
        model.addAttribute("index",index);
        model.addAttribute("conditions",conditions);
        model.addAttribute("types",types);
        model.addAttribute("statuses",statuses);


        return "listOffersView";
    }
    @RequestMapping(value = "/offers/{index}", method = RequestMethod.POST)
    public String offerListSearch(Model model ,@PathVariable("index") int index) {


        model.addAttribute("index",index);
        return "listOffersView";
    }
}

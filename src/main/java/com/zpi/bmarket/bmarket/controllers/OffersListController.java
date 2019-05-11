package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.DTO.SearchOfferDTO;
import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.OfferType;
import com.zpi.bmarket.bmarket.domain.Status;
import com.zpi.bmarket.bmarket.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OffersListController {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookConditionRepository bookConditionRepository;
    @Autowired
    private OfferTypeRepository offerTypeRepository;
    @Autowired
    private StatusRepository statusRepository;

    private int limit = 3;

    @ModelAttribute("conditions")
    public List<BookCondition> conditions(){
        return  Lists.newArrayList(bookConditionRepository.findAll());
    }

    @ModelAttribute("types")
    public List<OfferType> types(){
        return  Lists.newArrayList(offerTypeRepository.findAll());
    }

    @ModelAttribute("statuses")
    public List<Status> statuses(){
        return  Lists.newArrayList(statusRepository.findAll());
    }


    private Status getValidStatus(){
        return statusRepository.getFirstById(1L);
    }

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model, @PathVariable("index") int index) {
        List<Offer> offers = offerRepository.findAllByStatus(getValidStatus(), PageRequest.of(index - 1, limit)).getContent();

        model.addAttribute("offers", offers);
        model.addAttribute("index",index);
        model.addAttribute("searchOfferDTO",new SearchOfferDTO());


        return "listOffersView";
    }
    @RequestMapping(value = "/offers/{index}", method = RequestMethod.POST)
    public String offerListSearch(Model model , @PathVariable("index") int index, @ModelAttribute SearchOfferDTO searchOfferDTO) {

        searchOfferDTO.init();
        Pageable pageable = PageRequest.of(index - 1, limit);
        List<Offer> offers = offerRepository.findAllByStatusAndOfferTypeInAndBooksIn(
                getValidStatus(),searchOfferDTO.getOfferTypes(),
                bookRepository.findAllByBookConditionIn(searchOfferDTO.getConditions()),
                pageable).getContent();

//        OfferType type = searchOfferDTO.getOfferTypes().get(0);
//        List<Offer> offers = offerRepository.findAllByDescriptionContaining("Krople",PageRequest.of(index - 1, limit)).getContent();

        model.addAttribute("index",index);
        model.addAttribute("offers",offers);


        return "listOffersView";
    }
}

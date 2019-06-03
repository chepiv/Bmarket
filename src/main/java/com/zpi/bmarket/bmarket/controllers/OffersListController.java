package com.zpi.bmarket.bmarket.controllers;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.DTO.SearchOfferDTO;
import com.zpi.bmarket.bmarket.domain.*;
import com.zpi.bmarket.bmarket.repositories.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CategoryRepository categoryRepository;

    private int limit = 3;

    @ModelAttribute("conditions")
    public List<BookCondition> conditions() {
        return Lists.newArrayList(bookConditionRepository.findAll());
    }

    @ModelAttribute("types")
    public List<OfferType> types() {
        return Lists.newArrayList(offerTypeRepository.findAll());
    }

    @ModelAttribute("statuses")
    public List<Status> statuses() {
        return Lists.newArrayList(statusRepository.findAll());
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return Lists.newArrayList(categoryRepository.findAll());
    }


    private Status getValidStatus() {
        return statusRepository.getFirstById(1L);
    }

    private List<Offer> getOffersByDTO(SearchOfferDTO searchOfferDTO, int index) {
        Pageable pageable = PageRequest.of(index - 1, limit);
        List<Offer> offers;
        if (StringUtils.isEmpty(searchOfferDTO.getTextQuery()))
            offers = offerRepository.findAllByStatusAndOfferTypeInAndBooksInAndPriceBetween(
                    getValidStatus(), searchOfferDTO.getOfferTypes(),
                    bookRepository.findAllByBookConditionIn(searchOfferDTO.getConditions()),
                    searchOfferDTO.getPriceMin(), searchOfferDTO.getPriceMax(),
                    pageable).getContent();
        else
            offers = offerRepository.findAllByStatusAndOfferTypeInAndBooksInAndPriceBetweenAndTitleIsContaining(
                    getValidStatus(), searchOfferDTO.getOfferTypes(),
                    bookRepository.findAllByBookConditionIn(searchOfferDTO.getConditions()),
                    searchOfferDTO.getPriceMin(), searchOfferDTO.getPriceMax(),
                    searchOfferDTO.getTextQuery(),
                    pageable).getContent();
        if (searchOfferDTO.getCategory() != null) {
//            List<Book> booksByCat = bookRepository.findAllByCategory(searchOfferDTO.getCategory());
            offers = offers.stream().
                    filter(x -> x.getBooks().stream().anyMatch(
                            b -> b.getCategory() == searchOfferDTO.getCategory())).
                    collect(Collectors.toList());
        }
        return offers;

    }

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model, @PathVariable("index") int index) {
        SearchOfferDTO searchOfferDTO = new SearchOfferDTO();
        List<Offer> offers = getOffersByDTO(searchOfferDTO, index);

        model.addAttribute("offers", offers);
        model.addAttribute("index", index);
        model.addAttribute("searchOfferDTO", new SearchOfferDTO());


        return "listOffersView";
    }

    @RequestMapping(value = "/offers/{index}", method = RequestMethod.POST)
    public String offerListSearch(Model model, @PathVariable("index") int index, @ModelAttribute SearchOfferDTO searchOfferDTO) {
        List<Offer> offers = getOffersByDTO(searchOfferDTO, index);

        model.addAttribute("index", index);
        model.addAttribute("offers", offers);

        return "listOffersView";
    }
}

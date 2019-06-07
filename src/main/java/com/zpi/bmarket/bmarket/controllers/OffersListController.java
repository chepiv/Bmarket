package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.DTO.SearchOfferDTO;
import com.zpi.bmarket.bmarket.domain.*;
import com.zpi.bmarket.bmarket.repositories.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    private static final int limit = 10;

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

    private Page<Offer> getOffersByDTO(SearchOfferDTO searchOfferDTO, int index) {
        Pageable pageable = PageRequest.of(index - 1, limit);
        Page<Offer> offerPage;
        List<Book> booksSource = searchOfferDTO.getCategory()==null?
                bookRepository.findAllByBookConditionIn(searchOfferDTO.getConditions())
                :bookRepository.findAllByBookConditionInAndCategory(searchOfferDTO.getConditions(),searchOfferDTO.getCategory());
        if (StringUtils.isEmpty(searchOfferDTO.getTextQuery()))
            offerPage = offerRepository.findDistinctByStatusAndOfferTypeInAndBooksInAndPriceBetween(
                    getValidStatus(), searchOfferDTO.getOfferTypes(),
                    booksSource,
                    searchOfferDTO.getPriceMin(), searchOfferDTO.getPriceMax(),
                    pageable);
        else
            offerPage = offerRepository.findDistinctByStatusAndOfferTypeInAndBooksInAndPriceBetweenAndTitleIsContaining(
                    getValidStatus(), searchOfferDTO.getOfferTypes(),
                    booksSource,
                    searchOfferDTO.getPriceMin(), searchOfferDTO.getPriceMax(),
                    searchOfferDTO.getTextQuery(),
                    pageable);
        return offerPage;
    }

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return
                "redirect:/offers/1?textQuery=&category=&order-by=on&sale=true&_sale=on&exchange=true&_exchange=on&free=true&_free=on&new=true&_new=on&used=true&_used=on&priceMin=0&priceMax=9999";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model
            , @PathVariable("index") int index
            , @RequestParam(value = "query", required = false) String query
            , @RequestParam(value = "category", required = false) Integer catID
            , @RequestParam(value = "sale", required = false) Boolean typeS
            , @RequestParam(value = "exchange", required = false) Boolean typeE
            , @RequestParam(value = "free", required = false) Boolean typeF
            , @RequestParam(value = "new", required = false) Boolean conN
            , @RequestParam(value = "used", required = false) Boolean conU
            , @RequestParam(value = "priceMin", required = false) Integer priceMin
            , @RequestParam(value = "priceMax", required = false) Integer priceMax
    ) {
        SearchOfferDTO searchOfferDTO = new SearchOfferDTO();
        if (!StringUtils.isEmpty(query)) searchOfferDTO.setTextQuery(query);
        if (catID != null)
            searchOfferDTO.setCategory(categoryRepository.findById((long) catID).orElseThrow(() -> new IllegalArgumentException("catid: " + catID)));
        searchOfferDTO.setSale(typeS != null);
        searchOfferDTO.setExchange(typeE != null);
        searchOfferDTO.setFree(typeF != null);
        searchOfferDTO.setNew(conN != null);
        searchOfferDTO.setUsed(conU != null);
        if (priceMin != null)
            searchOfferDTO.setPriceMin(priceMin);
        if (priceMax != null)
            searchOfferDTO.setPriceMax(priceMax);
        Page<Offer> offerPage = getOffersByDTO(searchOfferDTO, index);
        searchOfferDTO.setPageable(PageRequest.of(index - 1, limit));

        model.addAttribute("offerPage", offerPage);
        model.addAttribute("index", index);
        model.addAttribute("searchOfferDTO", searchOfferDTO);


        return "listOffersView";
    }

//    @RequestMapping(value = "/offers/{index}", method = RequestMethod.POST)
//    public String offerListSearch(Model model, @PathVariable("index") int index, @ModelAttribute SearchOfferDTO searchOfferDTO) {
//        List<Offer> offers = getOffersByDTO(searchOfferDTO, index);
//        searchOfferDTO.setPageable(PageRequest.of(index - 1, limit));
//
//        model.addAttribute("index", index);
//        model.addAttribute("offers", offers);
//
//        return "listOffersView";
//    }
}

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
        if(((PageRequest) pageable).previous() == pageable);
        pageable = pageable.next();
        return offers;
    }

    @GetMapping("/offers")
    public String offerListStart(Model model) {
        return "redirect:/offers/1";
    }

    @GetMapping("/offers/{index}")
    public String offerList(Model model
                            ,@PathVariable("index") int index
//                            ,@RequestParam(value = "query",required = false) String query
////                            ,@RequestParam(value = "cat", required = false) int catID
//                            ,@RequestParam(value = "typeS", required = false) boolean typeS
//                            ,@RequestParam(value = "typeW", required = false) boolean typeW
//                            ,@RequestParam(value = "typeF", required = false) boolean typeF
//                            ,@RequestParam(value = "conN", required = false) boolean conN
//                            ,@RequestParam(value = "conU", required = false) boolean conU
//                            ,@RequestParam(value = "priceMin", required = false) int priceMin
//                            ,@RequestParam(value = "priceMax", required = false) int priceMax
    ) {
        SearchOfferDTO searchOfferDTO = new SearchOfferDTO();
//        if (!StringUtils.isEmpty(query)) {
//            searchOfferDTO.setTextQuery(query);
//        }
        List<Offer> offers = getOffersByDTO(searchOfferDTO, index);
        searchOfferDTO.setPageable(PageRequest.of(index-1,limit));

        model.addAttribute("offers", offers);
        model.addAttribute("index", index);
        model.addAttribute("searchOfferDTO", searchOfferDTO);


        return "listOffersView";
    }

    @RequestMapping(value = "/offers/{index}", method = RequestMethod.POST)
    public String offerListSearch(Model model, @PathVariable("index") int index, @ModelAttribute SearchOfferDTO searchOfferDTO) {
        List<Offer> offers = getOffersByDTO(searchOfferDTO, index);
        searchOfferDTO.setPageable(PageRequest.of(index-1,limit));

        model.addAttribute("index", index);
        model.addAttribute("offers", offers);

        return "listOffersView";
    }
}

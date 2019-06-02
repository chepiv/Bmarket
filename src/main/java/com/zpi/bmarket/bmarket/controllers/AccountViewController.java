package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountViewController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferRepository offerRepository;

    @GetMapping("/userAccount")
    public String viewAccount(HttpSession session, Model model){

        Long id = ((Long)session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        model.addAttribute("user",user);
        List<Offer> allOffers = Lists.newArrayList(offerRepository.findAll());
        List<Book> books = user.getBooks();
        List<Offer> usersOffers = new ArrayList<>();
        HashSet<Offer> offerHashSet = new HashSet<>();
        if(books!=null && !books.isEmpty()){

            for (Book book :
                    books) {
                long bookId = book.getId();
                usersOffers.addAll(allOffers.stream().
                        filter(x->x.getBooks().stream().
                                anyMatch(b->b.getId() == bookId)).
                        collect(Collectors.toList()));
            }
            usersOffers = new ArrayList<>(new HashSet<>(usersOffers));

        }
        model.addAttribute("offers",usersOffers);
        return "userAccountView";
    }
}

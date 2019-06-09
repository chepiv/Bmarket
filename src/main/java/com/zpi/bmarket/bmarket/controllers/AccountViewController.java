package com.zpi.bmarket.bmarket.controllers;

import com.google.common.collect.Lists;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.OfferRepository;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import com.zpi.bmarket.bmarket.services.UserAccount;
import com.zpi.bmarket.bmarket.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountViewController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/userAccount")
    public String viewAccount(HttpSession session, Model model) {
        User user = UsersService.getUser(session, userRepository);

        if(user == null)
            return "redirect:/login";

        model.addAttribute("user", user);
        List<Offer> allOffers = Lists.newArrayList(offerRepository.findAll());
        List<Book> books = user.getBooks();
        List<Offer> usersOffers = new ArrayList<>();
        //TODO: to sprawia że ładowanie strony trwa długo
        if (books != null && !books.isEmpty()) {

            for (Book book :
                    books) {
                long bookId = book.getId();
                usersOffers.addAll(allOffers.stream().
                        filter(x -> x.getBooks().stream().
                                anyMatch(b -> b.getId() == bookId
                                        && x.getStatus() != null && x.getStatus().getId() != 2)).//sprzedana
                        collect(Collectors.toList()));
            }
            usersOffers = new ArrayList<>(new HashSet<>(usersOffers));

        }
        model.addAttribute("offers", usersOffers);
        return "userAccountView";
    }

    @GetMapping("/postRemoveBookFromAccount/{id}")
    public String removeBookFromAccount(HttpSession session, Model model, @PathVariable Long id) {
        //remove book from offer and from user
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        UserAccount.removeBook(book);

        return "redirect:/userAccount";
    }

    @GetMapping("/postRemoveOfferFromAccount/{id}")
    public String removeOfferFromAccount(HttpSession session, Model model, @PathVariable Long id) {
        UserAccount.removeBooksFromOffer(id, offerRepository, bookRepository);


        return "redirect:/userAccount";
    }

    @GetMapping("postConfirmBuyOffer/{id}")
    public String confirmBuyOffer(HttpSession session, Model model, @PathVariable Long id) {

        Offer offer = offerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        UserAccount.processBuyOffer(offer, offerRepository, userRepository, statusRepository);

        return "redirect:/userAccount";
    }

}

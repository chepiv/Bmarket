package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chepiv on 08/04/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Controller
public class BooksListController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("books")
    public String getBooksListView(Model model){
        model.addAttribute("booksList",bookRepository.findAll());
        return "booksList";
    }
}

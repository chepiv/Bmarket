package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.BookToUserDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.CategoryRepository;
import com.zpi.bmarket.bmarket.repositories.ConditionRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserBookController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ConditionRepository conditionRepository;


    @GetMapping(value = "/addBook")
    public String getAddUserBook(Model model) {

        BookToUserDTO bookDTO = new BookToUserDTO();

        model.addAttribute("bookDTO", bookDTO);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("conditions", conditionRepository.findAll());

        return "addBookView";
    }

    @RequestMapping(value = "/postAddUserBook", method = RequestMethod.POST)
    public String postAddUserBook(@ModelAttribute BookToUserDTO bookDTO , Model model, HttpSession session) {

        PostStatus status = PostStatus.ERROR;
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        Book book = bookDTO.getBook(user);

        try {
            bookRepository.save(book);
            model.addAttribute("book", book);
            status = PostStatus.SUCCESS;
        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
        }


        model.addAttribute("status", status);
        return "postAddUserBookView";
    }
}

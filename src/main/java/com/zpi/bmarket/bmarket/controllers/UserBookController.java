package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.AddBookToUserDTO;
import com.zpi.bmarket.bmarket.DTO.LoginDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserBookController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;


    @GetMapping(value = "/addUserBook")
    public String getAddUserBook(Model model) {

        AddBookToUserDTO bookDTO = new AddBookToUserDTO();
        model.addAttribute("bookDTO", bookDTO);

        return "addUserBookView";
    }

    @RequestMapping(value = "/postAddUserBook", method = RequestMethod.POST)
    public String postAddUserBook(Model model, HttpSession session) {

        AddBookToUserDTO bookDTO = new AddBookToUserDTO();
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

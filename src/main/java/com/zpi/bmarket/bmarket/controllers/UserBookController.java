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
import java.util.Optional;

@Controller
public class UserBookController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;


    @GetMapping(value = "/addUserBook")
    public String getAddUserBook(Model model){

        AddBookToUserDTO bookDTO = new AddBookToUserDTO();
        model.addAttribute("bookDTO", bookDTO);

        return "addUserBookView";
    }

    @RequestMapping(value = "/postAddUserBook", method = RequestMethod.POST)
    public String postAddUserBook(@ModelAttribute AddBookToUserDTO bookDTO, Model model, HttpSession session){

        PostStatus status;
        Object userSessionId = session.getAttribute("userId");

        if(userSessionId == null){
            status = PostStatus.ERROR;
        }
        else {
            Optional<User> user = userRepository.findById((long)userSessionId);
            status = PostStatus.SUCCESS;
            Book book = bookDTO.getBook(user);

            try {
                bookRepository.save(book);
                model.addAttribute("book", book);
                status = PostStatus.SUCCESS;
            } catch (Exception e) {
                status = PostStatus.DATABASE_ERROR;
            }
        }



        model.addAttribute("status", status);
        return "postAddUserBookView";
    }
}

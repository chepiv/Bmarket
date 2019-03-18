package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chepiv on 18/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String getAddUserView(Model model, User user){
        model.addAttribute(user);
        return "userView";
    }

    @RequestMapping("/postUser")
    public String postUser(User user){
        userRepository.save(user);
        return "userView";
    }
}

package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model, User user){
        model.addAttribute("user",user);
        return "registerView";
    }

    @RequestMapping(value = "/postRegister" ,method = RequestMethod.POST)
    public String postRegister(@ModelAttribute User user) {
        userRepository.save(user);
        return "postRegisterView";
    }
}

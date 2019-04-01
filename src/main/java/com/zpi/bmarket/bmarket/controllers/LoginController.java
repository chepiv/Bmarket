package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.LoginDTO;
import com.zpi.bmarket.bmarket.PostLoginStatus;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import com.zpi.bmarket.bmarket.services.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/login")
    public String getLoginUserView(Model model){
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("login", loginDTO);
        return "loginView";
    }

    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(@ModelAttribute LoginDTO loginDTO, Model model){

        PostLoginStatus status = PostLoginStatus.SUCCESS;


        try {
            userRepository.findUserByLoginAndPassword(loginDTO.getUsername(), Encryption.encrypt(loginDTO.getPassword()));
        } catch (Exception e) {
            status = PostLoginStatus.WRONG_PASSWORD_OR_LOGIN;
        }

        model.addAttribute("user",loginDTO);
        model.addAttribute("status",status);
        return "postLoginView";
    }


}

package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.PostRegisterStatus;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserDTO;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "registerView";
    }

    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute UserDTO userDTO, Model model) {
        // TODO: 30.03.2019 in case of error do not redirect to post register view
        PostRegisterStatus status;
        //check for password matching
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            status = PostRegisterStatus.PASSWORDS_NOT_MATCH;
        } else {
            User user = userDTO.getUser();
            //add user to database
            try {
                userRepository.save(user);
                model.addAttribute("user", user);
                status = PostRegisterStatus.SUCCESS;
            } catch (Exception e) {
                // TODO: 30.03.2019 make two errors, for login and email
                status = PostRegisterStatus.DATABASE_ERROR;
            }
        }
        model.addAttribute("status", status);
        return "postRegisterView";
    }

}

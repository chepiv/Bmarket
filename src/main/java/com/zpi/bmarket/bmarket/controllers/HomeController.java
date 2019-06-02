package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String getHomeView(Model model, HttpSession session) {

        Long id = ((Long) session.getAttribute("userId")).longValue();
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("test", "test");
        }
        return "index";
    }


}

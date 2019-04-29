package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.LoginDTO;
import com.zpi.bmarket.bmarket.PostLoginStatus;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import com.zpi.bmarket.bmarket.services.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


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
    public String postLogin(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session, RedirectAttributes ra){

        PostStatus status = PostStatus.SUCCESS;
        User user = null;


        try {
            user = userRepository.findUserByLoginAndPassword(loginDTO.getUsername(), Encryption.encrypt(loginDTO.getPassword()));
        } catch (Exception e) {
            status = PostStatus.WRONG_PASSWORD_OR_USERNAME;
            ra.addFlashAttribute("redirectFrom", "postLogin");
            ra.addFlashAttribute("status", status);
            return "redirect:/login";
        }

        if (status == PostStatus.SUCCESS){
            session.setAttribute("userId", user.getId());
        }
        ra.addFlashAttribute("redirectFrom", "postLogin");
        ra.addFlashAttribute("status", status);
        return "redirect:/";
    }


}

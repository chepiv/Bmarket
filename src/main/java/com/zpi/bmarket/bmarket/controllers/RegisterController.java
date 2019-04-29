package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.RegisterDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        RegisterDTO user = new RegisterDTO();
        model.addAttribute("user", user);
        return "registerView";
    }

    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute RegisterDTO registerDTO, Model model, RedirectAttributes ra) {
        // TODO: 30.03.2019 in case of error do not redirect to post register view
        PostStatus status = PostStatus.ERROR;
        //check for password matching
        if (!registerDTO.getPassword().equals(registerDTO.getMatchingPassword())) {
            status = PostStatus.PASSWORDS_NOT_MATCH;
            ra.addFlashAttribute("redirectFrom", "postRegister");
            ra.addFlashAttribute("status", status);
            return "redirect:/register";
        } else {
            User user = registerDTO.getUser();
            //add user to database
            try {
                userRepository.save(user);
                model.addAttribute("user", user);
                status = PostStatus.SUCCESS;
                ra.addFlashAttribute("redirectFrom", "postRegister");
                ra.addFlashAttribute("status", status);
                return "redirect:/";
            } catch (Exception e) {
                // TODO: 30.03.2019 make two errors, for login and email
                status = PostStatus.DATABASE_ERROR;
                ra.addFlashAttribute("redirectFrom", "postRegister");
                ra.addFlashAttribute("status", status);
                return "redirect:/register";
            }
        }
    }

}

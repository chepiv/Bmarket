package com.zpi.bmarket.bmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MiscController {
    @GetMapping("/contact")
    public String contact(Model model) {
        return "contactView";
    }

    @GetMapping("/terms")
    public String terms(Model model) {
        return "termsView";
    }
}

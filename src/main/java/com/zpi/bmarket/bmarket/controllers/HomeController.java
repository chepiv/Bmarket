package com.zpi.bmarket.bmarket.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String getHomeView(){
        return "index";
    }
}

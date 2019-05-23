package com.zpi.bmarket.bmarket.services;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;

import javax.servlet.http.HttpSession;

public class StaticHelper {
    public static User getUser(Long id, UserRepository userRepository){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
    }
    public static User getUser(HttpSession session, UserRepository userRepository){
        return getUser(getUserIdFromSession(session),userRepository);
    }
    public static Long getUserIdFromSession(HttpSession session){
        return (Long) session.getAttribute("userId");
    }
}

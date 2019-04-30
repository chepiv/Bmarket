package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.UserEditDTO;
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
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

/**
 * Created by chepiv on 08/04/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Controller
public class EditUserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/userEdit")
    public String userEdit(HttpSession session, Model model) {
        //TODO: GET SESSION INFO AND IF USER LOGGED IN PASS USER DATA TO VIEW

        String sid = RequestContextHolder.currentRequestAttributes().getSessionId();
        UserEditDTO userEditDTO = new UserEditDTO();
        Long id = ((Long)session.getAttribute("userId")).longValue();
        User userRep = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        userEditDTO.getCurrentDataUser(userRep);

        model.addAttribute("userEditDTO", userEditDTO);
        model.addAttribute("sid", sid);
        return "userEditView";
    }


    @RequestMapping(value = "/postUserEdit", method = RequestMethod.POST)
    public String postUserEdit(@ModelAttribute UserEditDTO userEditDTO, User userRep, Model model) {
        PostStatus status = PostStatus.ERROR;
        /*
        if (!userEditDTO.getPassword().equals(userEditDTO.getMatchingPassword())) {
            status = PostStatus.PASSWORDS_NOT_MATCH;
        } else {
            status = PostStatus.DATABASE_ERROR;

            try {
                userEditDTO.setCurrentDataUser(userRep);
                userRepository.save(userRep);
                status = PostStatus.SUCCESS;
            } catch (Exception e) {
                status = PostStatus.DATABASE_ERROR;
            }

        }
        */

        model.addAttribute("status", status);
        return "postUserEditView";
    }

    /*
    @RequestMapping(value = "/postUserEdit", method = RequestMethod.POST)
    public String postUserEdit(@ModelAttribute UserEditDTO userEditDTO, User userRep, Model model) {
        PostRegisterStatus status = PostRegisterStatus.DATABASE_ERROR;
        userRep.setName("abc");
        System.out.println(userRep.getName());
        System.out.println(userRep.getSurname());
        System.out.println(userRep.getEmail());
        //User user = userEditDTO.getUser();
        //edit user in database
        try {
            userRepository.save(userRep);
            status = PostRegisterStatus.SUCCESS;
        } catch (Exception e) {
            status = PostRegisterStatus.DATABASE_ERROR;
        }
        model.addAttribute("status", status);
        return "postUserEditView";
    }

    @RequestMapping(value = "/postAddressEdit", method = RequestMethod.POST)
    public String postAddressEdit(@ModelAttribute UserEditDTO userEditDTO, Model model) {
        PostRegisterStatus status = PostRegisterStatus.DATABASE_ERROR;
        User user = userEditDTO.getUser();
        //edit user in database
        try {
            userRepository.save(user);
            status = PostRegisterStatus.SUCCESS;
        } catch (Exception e) {
            status = PostRegisterStatus.DATABASE_ERROR;
        }
        model.addAttribute("status", status);
        return "postUserEditView";
    }

    @RequestMapping(value = "/postContactEdit", method = RequestMethod.POST)
    public String postContactEdit(@ModelAttribute UserEditDTO userEditDTO, Model model) {
        PostRegisterStatus status = PostRegisterStatus.DATABASE_ERROR;
        User user = userEditDTO.getUser();
        //edit user in database
        try {
            userRepository.save(user);
            status = PostRegisterStatus.SUCCESS;
        } catch (Exception e) {
            status = PostRegisterStatus.DATABASE_ERROR;
        }
        model.addAttribute("status", status);
        return "postUserEditView";
    }

    @RequestMapping(value = "/postPasswordEdit", method = RequestMethod.POST)
    public String postPasswordEdit(@ModelAttribute UserEditDTO userEditDTO, Model model) {
        PostRegisterStatus status = PostRegisterStatus.DATABASE_ERROR;
        User user = userEditDTO.getUser();
        if (!userEditDTO.getPassword().equals(userEditDTO.getMatchingPassword())) {
            status = PostRegisterStatus.PASSWORDS_NOT_MATCH;
        } else {
            //edit user in database
            try {
                userRepository.save(user);
                status = PostRegisterStatus.SUCCESS;
            } catch (Exception e) {
                status = PostRegisterStatus.DATABASE_ERROR;
            }
        }
        model.addAttribute("status", status);
        return "postUserEditView";
    }
    */



}

package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.UserDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Address;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.AddressRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by chepiv on 08/04/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Controller
public class EditUserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }

    @GetMapping("/userEdit")
    public String userEdit(HttpSession session, Model model) {
        //TODO: GET SESSION INFO AND IF USER LOGGED IN PASS USER DATA TO VIEW

        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        UserDTO userDTO = new UserDTO();
        userDTO.getCurrentDataUser(user);

        model.addAttribute("userDTO", userDTO);


        return "userEditView";
    }


    @RequestMapping(value = "/postUserEdit", method = RequestMethod.POST)
    public String postUserEdit(HttpSession session, UserDTO userDTO, Model model) {

        PostStatus status = PostStatus.ERROR;
        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        try {
            userDTO.setNameSurnamePhoneAvatar(user);
            userRepository.save(user);
            status = PostStatus.SUCCESS;
        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("status", status);

        return "postUserEditView";
    }


    @GetMapping("/changePassword")
    public String changePassword(HttpSession session, Model model) {

        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        UserDTO userDTO = new UserDTO();
        userDTO.getCurrentDataUser(user);

        model.addAttribute("user", user);
        model.addAttribute("userDTO", userDTO);

        return "changePasswordView";
    }

    @RequestMapping(value = "/postChangePassword", method = RequestMethod.POST)
    public String postChangePassword(Model model, UserDTO userDTO, User user) {

        PostStatus status = PostStatus.ERROR;

        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            status = PostStatus.PASSWORDS_NOT_MATCH;
        } else {
            status = PostStatus.DATABASE_ERROR;

            try {
                userDTO.setPassword(user);
                userRepository.save(user);
                status = PostStatus.SUCCESS;
            } catch (Exception e) {
                status = PostStatus.DATABASE_ERROR;
            }

        }

        model.addAttribute("status", status);

        return "postChangePasswordView";
    }


    @GetMapping("/changeAddress")
    public String changeaddress(HttpSession session, Model model) {

        Long id = ((Long) session.getAttribute("userId")).longValue();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));
        UserDTO userDTO = new UserDTO();
        userDTO.getCurrentDataUser(user);
        userDTO.setAddressString(user.getAddress());

        model.addAttribute("user", user);
        model.addAttribute("userDTO", userDTO);

        return "changeAddressView";
    }


    @RequestMapping(value = "/postChangeAddress", method = RequestMethod.POST)
    public String postChangeAddress(Model model, UserDTO userDTO, User user) {

        PostStatus status = PostStatus.ERROR;
        try {
            if (addressRepository.existsByCityAndAndStreetAddressAndZipCode(userDTO.getCity(), userDTO.getStreetAddress(), userDTO.getZipCode())) {
                Address address = addressRepository.findByCityAndStreetAddressAndZipCode(userDTO.getCity(), userDTO.getStreetAddress(), userDTO.getZipCode());
                user.setAddress(address);
                userRepository.save(user);
                status = PostStatus.SUCCESS;
            } else {
                Address address = userDTO.createAddress();
                user.setAddress(address);
                userRepository.save(user);
                //addressRepository.save(address);
                status = PostStatus.SUCCESS;
            }
        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
        }

        model.addAttribute("status", status);

        return "postChangeAddressView";
    }

    /*
    @RequestMapping(value = "/postUserEdit", method = RequestMethod.POST)
    public String postUserEdit(@ModelAttribute UserDTO userEditDTO, User userRep, Model model) {
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
    public String postAddressEdit(@ModelAttribute UserDTO userEditDTO, Model model) {
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
    public String postContactEdit(@ModelAttribute UserDTO userEditDTO, Model model) {
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
    public String postPasswordEdit(@ModelAttribute UserDTO userEditDTO, Model model) {
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

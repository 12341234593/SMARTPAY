package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.dto.UserSignupDTO;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.repository.UserRepository;
import com.Payment.MobiPayLite.role.Role;
import com.Payment.MobiPayLite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthUiController {

    @Autowired
    private UserRepository userRepository;


@GetMapping("/login")

    public String loginPage()
    {
        return "user/merchant/auth/admin/login";
    }

    @GetMapping("/register")
    public String registerPage()
    {
        return "user/merchant/auth/register";
    }
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("user") UserSignupDTO userDTO) {
        authService.registerUser(userDTO);

// call service
        return "redirect:/auth/login";
    }


    @GetMapping("/forgot")
    public String forgotPage()
    {
        return "auth/forgot";
    }


}

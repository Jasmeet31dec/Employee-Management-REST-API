package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "employees/fancy-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "employees/access-denied";
    }

}

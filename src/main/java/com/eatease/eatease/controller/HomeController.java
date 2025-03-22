package com.eatease.eatease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login-page")
    public String loginPage() {
        return "login"; // Aponta para login.html em src/main/resources/templates/
    }

    @GetMapping("/register-page")
    public String registerPage() {
        return "register"; // Aponta para register.html
    }
}

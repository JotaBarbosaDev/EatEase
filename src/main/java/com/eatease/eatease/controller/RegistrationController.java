package com.eatease.eatease.controller;

import com.eatease.eatease.dto.UserDTO;
import com.eatease.eatease.model.User;
import com.eatease.eatease.service.UserService;
import jakarta.validation.Valid;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register/CreateUser")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";  // Aponta para o template register.html
    }

    @PostMapping("/register/CreateUser")
    public String processRegistration(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Se houver erros, volta ao formulário e mostra as mensagens
            System.err.println("Erro no registo: " + bindingResult.getAllErrors());
            model.addAttribute("userDTO", userDTO);
            return "register";
        }

        // Caso os dados sejam válidos, mapeia para a entidade User
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // Define um role padrão, por exemplo "USER"
        user.setRoles(Set.of("USER"));

        // Chama o serviço que grava o user na BD (ver o ponto 3)
        userService.save(user);

        // Após o registo, redireciona para a página de login (ou outra)
        return "redirect:/login";
    }
}

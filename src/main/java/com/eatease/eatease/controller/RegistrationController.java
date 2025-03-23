package com.eatease.eatease.controller;

import com.eatease.eatease.dto.FuncionarioDTO;
import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/register/CreateFuncionario")
    public String showFuncionarioRegistrationForm(Model model) {
        model.addAttribute("funcionarioDTO", new FuncionarioDTO());
        return "registerFuncionario";  // Um template adaptado para criar funcionários
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/CreateFuncionario")
    public String processFuncionarioRegistration(@Valid @ModelAttribute("funcionarioDTO") FuncionarioDTO funcionarioDTO,
                                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerFuncionario";
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setUsername(funcionarioDTO.getUsername());
        funcionario.setPassword(passwordEncoder.encode(funcionarioDTO.getPassword()));
        funcionario.setCargo_id(funcionarioDTO.getCargoId()); // ou lógica para definir o cargo
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setTelefone(funcionarioDTO.getTelefone());

        funcionarioRepository.save(funcionario);

        return "redirect:/home";
    }
}

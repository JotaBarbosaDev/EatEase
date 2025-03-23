package com.eatease.eatease.service;

import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.repository.FuncionarioRepository;
import com.eatease.eatease.config.CustomFuncionarioDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomFuncionarioDetailsService implements UserDetailsService {
    
    private final FuncionarioRepository funcionarioRepository;

    public CustomFuncionarioDetailsService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Funcionario funcionario = funcionarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado"));
    return new CustomFuncionarioDetails(funcionario);
}
}

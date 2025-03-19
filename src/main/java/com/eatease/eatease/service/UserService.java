package com.eatease.eatease.service;

import com.eatease.eatease.model.User;
import com.eatease.eatease.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(String username, String nome, String password, long cargo_id) {
        
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setNome(nome);
            user.setUsername(username);
            user.setPassword(password);
            user.setCargo_id(cargo_id);
            userRepository.save(user);
            System.err.println("User adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O username já existe.");
            return false;
        }
    }
}

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

    public User save(User user) {
        // Aqui podes adicionar lógica extra, por exemplo, verificar se o username já existe
        return userRepository.save(user);
    }
}

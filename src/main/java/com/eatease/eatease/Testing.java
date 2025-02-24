package com.eatease.eatease;

import com.eatease.eatease.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Testing {

    private final UserService userService;

    public Testing(UserService userService) {
        this.userService = userService;
    }

    public void criar() {
        userService.createUser("João Silva", "joao@example.com");
        userService.createUser("weewoewofweohfewuh Oliveira", "maria@example.com");
        System.out.println("Utilizadores criados com sucesso!");
    }
}

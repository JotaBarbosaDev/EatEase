package com.eatease.eatease.controller;

import com.eatease.eatease.model.User;
import com.eatease.eatease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Listar todos os utilizadores
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obter um utilizador por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    static class UserRequest{
        private String nome; 
        private String username;
        private String password;
        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
        return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }   
    }

    // Criar um novo utilizador
    @PostMapping("/createUser")
    public User createUser(@RequestBody UserRequest userReq) {
        User user = new User();
        user.setNome(userReq.getNome());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        
        return userRepository.save(user);
    }

    // Atualizar um utilizador existente
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setNome(userDetails.getNome());
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setCargo_id(userDetails.getCargo_id());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // Apagar um utilizador
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

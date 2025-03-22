
package com.eatease.eatease.service;

import com.eatease.eatease.model.User;
import com.eatease.eatease.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Utilizador não encontrado: " + username);
        }

        User user = userOptional.get();

        List<GrantedAuthority> authorities = user.getRoles() != null
                ? user.getRoles().stream()
                      .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                      .collect(Collectors.toList())
                : List.of();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), 
                authorities
        );
    }
}

package com.eatease.eatease.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
            .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar testes
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register/**", "/css/**", "/js/**").permitAll() // Permite acesso a login, register e assets
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")               // Tua página de login personalizada
                .loginProcessingUrl("/login")      // URL para processar o login (o Spring cuidará disso)
                .defaultSuccessUrl("/", true)      // Página de destino após o login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")              // URL para logout
                .logoutSuccessUrl("/login")        // Redireciona para o login após o logout
                .permitAll()
            );

         return http.build();
    }
}

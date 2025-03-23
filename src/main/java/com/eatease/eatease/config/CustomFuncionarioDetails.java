package com.eatease.eatease.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eatease.eatease.model.Funcionario;

public class CustomFuncionarioDetails implements UserDetails {
    private final Funcionario funcionario;

    public CustomFuncionarioDetails(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getId() {
        return funcionario.getId();
    }

    public String getNome() {
        return funcionario.getNome();
    }

    public String getCargo() {
        return funcionario.getCargo_id();
    }
    
    public String getEmail() {
        return funcionario.getEmail();
    }
    
    public String getTelefone() {
        return funcionario.getTelefone();
    }

    @Override
    public String getUsername() {
        return funcionario.getUsername();
    }
    
    @Override
    public String getPassword() {
        return funcionario.getPassword();
    }
    
    @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // Se o cargo_id for "2", consideramos o utilizador como Gerente; caso contrário, USER.
    String role = funcionario.getCargo_id().trim().equals("2") ? "GERENTE" : "FUNCIONARIO";
return List.of(new SimpleGrantedAuthority("ROLE_" + role));


}


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}

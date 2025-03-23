package com.eatease.eatease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FuncionarioDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 3, max = 30, message = "O username deve ter entre 3 e 30 caracteres")
    private String username;

    @NotBlank(message = "A password é obrigatória")
    @Size(min = 6, message = "A password deve ter no mínimo 6 caracteres")
    private String password;

    @NotBlank(message = "O cargo é obrigatório")
    private String cargoId;

    private String email;
    private String telefone;

    // Getters e Setters
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
    public String getCargoId() {
        return cargoId;
    }
    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

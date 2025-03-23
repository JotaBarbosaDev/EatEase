package com.eatease.eatease.service;

import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public boolean createFuncionario(String nome, String cargo_id, String username, String password, String email, String telefone) {
        if (funcionarioRepository.findByUsername(username).isEmpty()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setCargo_id(cargo_id);
            funcionario.setUsername(username);
            funcionario.setPassword(password);
            funcionario.setEmail(email);
            funcionario.setTelefone(telefone);
            funcionarioRepository.save(funcionario);
            System.err.println("Funcionario adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O funcionario já existe.");
            return false;
        }
        
    }
}

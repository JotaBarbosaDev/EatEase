package com.eatease.eatease.service;

import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.model.Cargo;
import com.eatease.eatease.repository.FuncionarioRepository;
import com.eatease.eatease.repository.CargoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }

    public boolean createFuncionario(String nome, Long cargoId, String username, String password, String email, String telefone) {
        if (funcionarioRepository.findByUsername(username).isEmpty()) {
            Optional<Cargo> cargoOptional = cargoRepository.findById(cargoId);
            if (cargoOptional.isEmpty()) {
                System.err.println("Cargo não encontrado.");
                return false;
            }
            Cargo cargo = cargoOptional.get();
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setCargo(cargo); // Define a associação com o Cargo
            funcionario.setUsername(username);
            funcionario.setPassword(password);
            funcionario.setEmail(email);
            funcionario.setTelefone(telefone);
            funcionarioRepository.save(funcionario);
            System.err.println("Funcionário adicionado com sucesso.");
            return true;
        } else {
            System.err.println("O funcionário já existe.");
            return false;
        }
    }
}

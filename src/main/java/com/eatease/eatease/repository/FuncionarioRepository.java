package com.eatease.eatease.repository;

import com.eatease.eatease.model.Funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    Optional<Funcionario> findByNome(String nome); 
}

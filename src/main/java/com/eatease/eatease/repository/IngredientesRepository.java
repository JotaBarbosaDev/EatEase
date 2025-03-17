package com.eatease.eatease.repository;

import com.eatease.eatease.model.Ingredientes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Long> {
    
    Optional<Ingredientes> findByNome(String nome);
}

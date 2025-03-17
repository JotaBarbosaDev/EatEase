package com.eatease.eatease.repository;

import com.eatease.eatease.model.MovimentosIngredientes;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentosIngredientesRepository extends JpaRepository<MovimentosIngredientes, Long> {
    //Optional<MovimentosIngredientes> findByNome(String nome);
}

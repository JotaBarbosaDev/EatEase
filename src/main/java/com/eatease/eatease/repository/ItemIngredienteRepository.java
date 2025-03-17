package com.eatease.eatease.repository;

import com.eatease.eatease.model.ItemIngrediente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemIngredienteRepository extends JpaRepository<ItemIngrediente, Long> {
    Optional<ItemIngrediente> findByIngredientes(long ingredientes_id);
}

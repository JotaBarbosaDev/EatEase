package com.eatease.eatease.repository;

import com.eatease.eatease.model.UnidadeMedida;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {
    Optional<UnidadeMedida> findByNome(String nome);
}

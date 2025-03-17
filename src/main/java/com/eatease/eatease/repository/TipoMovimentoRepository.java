package com.eatease.eatease.repository;

import com.eatease.eatease.model.TipoMovimento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimentoRepository extends JpaRepository<TipoMovimento, Long> {
    Optional<TipoMovimento> findByNome(String nome);
}

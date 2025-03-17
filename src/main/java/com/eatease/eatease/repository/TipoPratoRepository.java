package com.eatease.eatease.repository;

import com.eatease.eatease.model.TipoPrato;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPratoRepository extends JpaRepository<TipoPrato, Long> {
    Optional<TipoPrato> findByNome(String nome);
}

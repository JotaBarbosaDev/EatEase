package com.eatease.eatease.repository;

import com.eatease.eatease.model.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNome(String nome);

    Optional<List<Item>> findByTipoPratoId(long tipoPratoId);
}

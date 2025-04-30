package com.eatease.eatease.repository;

import com.eatease.eatease.model.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNome(String nome);

    @Query(value = "SELECT * FROM ITEM WHERE tipoPrato_id = ?1", nativeQuery = true)
    List<Item> findByTipoPratoId(long tipoPrato_id);
}

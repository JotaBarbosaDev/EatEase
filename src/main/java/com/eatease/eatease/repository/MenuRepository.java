package com.eatease.eatease.repository;

import com.eatease.eatease.model.Menu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    //Optional<Menu> findByPratos(long pratos_id[]);
    Optional<Menu> findByNome(String nome);
}

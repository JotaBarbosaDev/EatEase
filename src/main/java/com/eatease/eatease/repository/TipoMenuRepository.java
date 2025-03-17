package com.eatease.eatease.repository;

import com.eatease.eatease.model.TipoMenu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMenuRepository extends JpaRepository<TipoMenu, Long> {
    Optional<TipoMenu> findByNome(String nome);
}

package com.eatease.eatease.repository;

import com.eatease.eatease.model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    // Optional<User> findByEmail(String email); // Encontrar usuário pelo e-mail
}

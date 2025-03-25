package com.eatease.eatease.config;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionChecker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void checkDatabaseConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("✅ Conexão a base de dados bem sucedida!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar a base de dados: " + e.getMessage());
        }
    }
}

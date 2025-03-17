package com.eatease.eatease.service;

import com.eatease.eatease.model.Ingredientes;
import com.eatease.eatease.repository.IngredientesRepository;

import org.springframework.stereotype.Service;

@Service
public class IngredientesService {

    private final IngredientesRepository ingredientesRepository;

    public IngredientesService(IngredientesRepository ingredientesRepository) {
        this.ingredientesRepository = ingredientesRepository;
    }

    public boolean createIngredientes(String nome, int stock, int stock_min, String unidade_id) {
        if (ingredientesRepository.findByNome(nome).isEmpty()) {
            Ingredientes ingredientes = new Ingredientes();
            ingredientes.setNome(nome);
            ingredientes.setStock(stock_min);
            ingredientes.setStock_min(stock_min);
            ingredientes.setUnidade_id(unidade_id);
            ingredientesRepository.save(ingredientes);
            System.err.println("Ingrediente adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O ingredientes já existe.");
            return false;
        }
    }
}

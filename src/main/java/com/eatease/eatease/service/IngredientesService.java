package com.eatease.eatease.service;

import com.eatease.eatease.model.Ingredientes;
import com.eatease.eatease.repository.IngredientesRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IngredientesService {

    private final IngredientesRepository ingredientesRepository;
    private final UnidadeMedidaService unidadeMedidaService;

    public IngredientesService(IngredientesRepository ingredientesRepository,
            UnidadeMedidaService unidadeMedidaService) {
        this.ingredientesRepository = ingredientesRepository;
        this.unidadeMedidaService = unidadeMedidaService;
    }

    public boolean createIngredientes(String nome, int stock, int stock_min, String unidade) {
        // Verifica se a unidade de medida existe
        long unidade_id = unidadeMedidaService.getUnidadeMedidaId(unidade);
        if (unidade_id == -1) {
            System.err.println("A unidade de medida não existe.");
            return false;
        }

        if (ingredientesRepository.findByNome(nome).isEmpty()) {
            Ingredientes ingredientes = new Ingredientes();
            ingredientes.setNome(nome);
            ingredientes.setStock(stock); // use provided stock instead of stock_min
            ingredientes.setStock_min(stock_min);
            ingredientes.setUnidade_id(unidade_id);
            ingredientesRepository.save(ingredientes);
            System.err.println("Ingrediente adicionado com sucesso.");
            return true;
        } else {
            System.err.println("O ingredientes já existe.");
            return false;
        }
    }

    public List<Ingredientes> getAllIngredientes() {
        return ingredientesRepository.findAll();
    }

    public Ingredientes getIngredienteById(long id) {
        return ingredientesRepository.findById(id).orElse(null);
    }

    public boolean updateIngredientes(long id, String nome, int stock, int stock_min, String unidade) {
        // Verifica se a unidade de medida existe
        long unidade_id = unidadeMedidaService.getUnidadeMedidaId(unidade);
        if (unidade_id == -1) {
            System.err.println("A unidade de medida não existe.");
            return false;
        }

        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            ingredientes.setNome(nome);
            ingredientes.setStock(stock);
            ingredientes.setStock_min(stock_min);
            ingredientes.setUnidade_id(unidade_id);
            ingredientesRepository.save(ingredientes);
            System.err.println("Ingrediente atualizado com sucesso.");
            return true;
        } else {
            System.err.println("O ingrediente não existe.");
            return false;
        }
    }

    public boolean deleteIngredientes(long id) {
        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            ingredientesRepository.delete(ingredientes);
            System.err.println("Ingrediente removido com sucesso.");
            return true;
        } else {
            System.err.println("O ingrediente não existe.");
            return false;
        }
    }

    public boolean addStock(long id, int stock) {
        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            ingredientes.setStock(ingredientes.getStock() + stock);
            ingredientesRepository.save(ingredientes);
            System.err.println("Stock adicionado com sucesso.");
            return true;
        } else {
            System.err.println("O ingrediente não existe.");
            return false;
        }
    }

    public boolean removeStock(long id, int stock) {
        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            if (ingredientes.getStock() - stock < 0) {
                System.err.println("Stock insuficiente.");
                return false;
            }
            ingredientes.setStock(ingredientes.getStock() - stock);
            ingredientesRepository.save(ingredientes);
            System.err.println("Stock removido com sucesso.");
            return true;
        } else {
            System.err.println("O ingrediente não existe.");
            return false;
        }
    }
}

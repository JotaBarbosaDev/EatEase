package com.eatease.eatease.service;

import com.eatease.eatease.model.Ingredientes;
import com.eatease.eatease.repository.IngredientesRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientesService {

    private final IngredientesRepository ingredientesRepository;
    private final UnidadeMedidaService unidadeMedidaService;

    public IngredientesService(IngredientesRepository ingredientesRepository,
            UnidadeMedidaService unidadeMedidaService) {
        this.ingredientesRepository = ingredientesRepository;
        this.unidadeMedidaService = unidadeMedidaService;
    }

    public String createIngredientes(String nome, int stock, int stock_min, String unidade) {
        // Verifica se a unidade de medida existe
        Long unidade_id = unidadeMedidaService.getUnidadeMedidaId(unidade);
        if (unidade_id == null || unidade_id == -1) {
            System.err.println("A unidade de medida não existe.");
            return "A unidade de medida não existe.";
        }

        if (ingredientesRepository.findByNome(nome).isEmpty()) {
            Ingredientes ingredientes = new Ingredientes();
            ingredientes.setNome(nome);
            ingredientes.setStock(stock); // use provided stock instead of stock_min
            ingredientes.setStock_min(stock_min);
            ingredientes.setUnidade_id(unidade_id);
            ingredientesRepository.save(ingredientes);
            System.err.println("Ingrediente adicionado com sucesso.");
            return null;
        } else {
            System.err.println("O ingredientes já existe.");
            return "O ingredientes já existe.";
        }
    }

    public List<Ingredientes> getAllIngredientes() {
        return ingredientesRepository.findAll();
    }

    public Ingredientes getIngredienteById(long id) {
        return ingredientesRepository.findById(id).orElse(null);
    }

    public String updateIngredientes(long id, String nome, int stock, int stock_min, String unidade) {
        // Verifica se a unidade de medida existe
        long unidade_id = unidadeMedidaService.getUnidadeMedidaId(unidade);
        if (unidade_id == -1) {
            System.err.println("A unidade de medida não existe.");
            return "A unidade de medida não existe.";
        }

        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            ingredientes.setNome(nome);
            ingredientes.setStock(stock);
            ingredientes.setStock_min(stock_min);
            ingredientes.setUnidade_id(unidade_id);
            ingredientesRepository.save(ingredientes);
            System.err.println("Ingrediente atualizado com sucesso.");
            return "Ingrediente atualizado com sucesso.";
        } else {
            System.err.println("O ingrediente não existe.");
            return null;
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

    /**
     * Removes specified quantity from ingredient stock with transaction support
     * to maintain data consistency and prevent race conditions
     */
    @Transactional
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

    public boolean doesIngredienteExist(long id) {
        return ingredientesRepository.existsById(id);
    }

    /**
     * Checks if an ingredient has enough stock for the required quantity
     * Read-only to optimize database access
     */
    @Transactional(readOnly = true)
    public boolean temStockSuficiente(long id, int stock) {
        Ingredientes ingredientes = ingredientesRepository.findById(id).orElse(null);
        if (ingredientes != null) {
            return ingredientes.getStock() >= stock;
        } else {
            System.err.println("O ingrediente não existe.");
            return false;
        }
    }
}

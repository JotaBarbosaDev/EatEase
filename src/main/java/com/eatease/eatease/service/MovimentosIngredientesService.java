package com.eatease.eatease.service;

import com.eatease.eatease.model.Ingredientes;
import com.eatease.eatease.model.MovimentosIngredientes;
import com.eatease.eatease.repository.MovimentosIngredientesRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimentosIngredientesService {

    private final MovimentosIngredientesRepository movimentosIngredientesRepository;
    private final IngredientesService ingredientesService;

    public MovimentosIngredientesService(MovimentosIngredientesRepository movimentosIngredientesRepository,
            IngredientesService ingredientesService) {
        this.movimentosIngredientesRepository = movimentosIngredientesRepository;
        this.ingredientesService = ingredientesService;
    }

    public int createMovimentoIngrediente(long id_ingrediente, int quantidade) {
        Ingredientes ingrediente = ingredientesService.getIngredienteById(id_ingrediente);
        if (ingrediente == null) {
            System.err.println("Ingrediente não encontrado.");
            throw new IllegalArgumentException("Ingrediente não encontrado.");
        }

        if (quantidade < 0) {
            ingredientesService.removeStock(id_ingrediente, -quantidade);
        } else {
            ingredientesService.addStock(id_ingrediente, quantidade);
        }

        MovimentosIngredientes movimentosIngredientes = new MovimentosIngredientes();
        movimentosIngredientes.setId_ingrediente(id_ingrediente);
        movimentosIngredientes.setQuantidade_anterior(ingrediente.getStock() - quantidade); // Defina o valor anterior
                                                                                            // conforme
        movimentosIngredientes.setQuantidade_atualizada(quantidade); // Defina o valor atualizado conforme necessário
        movimentosIngredientes.setData(java.time.LocalDate.now().toString()); // Set current date to today
        movimentosIngredientesRepository.save(movimentosIngredientes);
        
        System.err.println("Movimento criado com sucesso.");
        return ingrediente.getStock(); // quantidade atualizada;
    }
}

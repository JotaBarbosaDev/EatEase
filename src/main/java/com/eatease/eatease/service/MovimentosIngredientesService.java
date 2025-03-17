package com.eatease.eatease.service;

import com.eatease.eatease.model.MovimentosIngredientes;
import com.eatease.eatease.repository.MovimentosIngredientesRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimentosIngredientesService {

    private final MovimentosIngredientesRepository movimentosIngredientesRepository;

    public MovimentosIngredientesService(MovimentosIngredientesRepository movimentosIngredientesRepository) {
        this.movimentosIngredientesRepository = movimentosIngredientesRepository;
    }

    public boolean createMovimentoIngrediente(long id_ingrediente, long tipoMovimento_id) {
            MovimentosIngredientes movimentosIngredientes = new MovimentosIngredientes();
            movimentosIngredientes.setId_ingrediente(id_ingrediente);
            movimentosIngredientes.setTipoMovimento_id(tipoMovimento_id);
            movimentosIngredientesRepository.save(movimentosIngredientes);
            System.err.println("Movimento criado com sucesso.");
            return true;
    }
}

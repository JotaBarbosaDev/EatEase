package com.eatease.eatease.service;

import com.eatease.eatease.model.ItemIngrediente;
import com.eatease.eatease.repository.ItemIngredienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemIngredienteService {

    private final ItemIngredienteRepository itemIngredienteRepository;

    public ItemIngredienteService(ItemIngredienteRepository itemIngredienteRepository) {
        this.itemIngredienteRepository = itemIngredienteRepository;
    }

    public boolean createItemIngrediente(long ingredientes_id, int quantidade) {
        if (itemIngredienteRepository.findByIngredientes(ingredientes_id).isEmpty()) {
            ItemIngrediente itemIngrediente = new ItemIngrediente();
            itemIngrediente.setIngredientes_id(ingredientes_id);
            itemIngrediente.setQuantidade(quantidade);
            System.err.println("Item ingrediente adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O Item ingrediente já existe.");
            return false;
        }
    }
}

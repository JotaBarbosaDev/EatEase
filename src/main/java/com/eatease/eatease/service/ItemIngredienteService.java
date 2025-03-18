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
            ItemIngrediente itemIngrediente = new ItemIngrediente();
            itemIngrediente.setIngredientes_id(ingredientes_id);
            itemIngrediente.setQuantidade(quantidade);
            itemIngredienteRepository.save(itemIngrediente);
            System.err.println("Item ingrediente adicionado com sucesso.");
            return true;
    }
}

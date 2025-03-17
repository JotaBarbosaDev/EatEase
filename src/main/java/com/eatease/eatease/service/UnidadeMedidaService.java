package com.eatease.eatease.service;

import com.eatease.eatease.model.UnidadeMedida;
import com.eatease.eatease.repository.UnidadeMedidaRepository;
import org.springframework.stereotype.Service;

@Service
public class UnidadeMedidaService {

    private final UnidadeMedidaRepository unidadeMedidaRepository;

    public UnidadeMedidaService(UnidadeMedidaRepository unidadeMedidaRepository) {
        this.unidadeMedidaRepository = unidadeMedidaRepository;
    }

    public boolean createUnidadeMedida(String nome, long ingrediente_id) {
        if (unidadeMedidaRepository.findByNome(nome).isEmpty()) {
            UnidadeMedida unidadeMedida = new UnidadeMedida();
            unidadeMedida.setNome(nome);
            unidadeMedida.setIngrediente_id(ingrediente_id);
            unidadeMedidaRepository.save(unidadeMedida);
            System.err.println("Unidade de medida adicionada com sucesso.");
            return true;
        }else{
            System.err.println("A unidade de medida já existe.");
            return false;
        }
    }
}

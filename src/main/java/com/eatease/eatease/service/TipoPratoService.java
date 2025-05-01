package com.eatease.eatease.service;

import com.eatease.eatease.model.TipoPrato;
import com.eatease.eatease.repository.TipoPratoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoPratoService {

    private final TipoPratoRepository tipoPratoRepository;

    public TipoPratoService(TipoPratoRepository tipoPratoRepository) {
        this.tipoPratoRepository = tipoPratoRepository;
    }

    public boolean createTipoPrato(String nome) {
        if (tipoPratoRepository.findByNome(nome).isEmpty()) {
            TipoPrato tipoPrato = new TipoPrato();
            tipoPrato.setNome(nome);
            tipoPratoRepository.save(tipoPrato);
            System.err.println("Tipo de prato adicionado com sucesso.");
            return true;
        } else {
            System.err.println("O tipo de prato já existe.");
            return false;
        }
    }

    public List<TipoPrato> getAllTipoPratos() {
        return tipoPratoRepository.findAll();
    }

    public boolean checkTipoPratoExists(Long id) {
        return tipoPratoRepository.existsById(id);
    }
}

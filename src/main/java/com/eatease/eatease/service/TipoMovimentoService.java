package com.eatease.eatease.service;

import com.eatease.eatease.model.TipoMovimento;
import com.eatease.eatease.repository.TipoMovimentoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoMovimentoService {

    private final TipoMovimentoRepository tipoMovimentoRepository;

    public TipoMovimentoService(TipoMovimentoRepository tipoMovimentoRepository) {
        this.tipoMovimentoRepository = tipoMovimentoRepository;
    }

    public boolean createTipoMovimento(String nome) {
        if (tipoMovimentoRepository.findByNome(nome).isEmpty()) {
            TipoMovimento tipoMovimento = new TipoMovimento();
            tipoMovimento.setNome(nome);
            tipoMovimentoRepository.save(tipoMovimento);
            System.err.println("Tipo de movimento adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O tipo de movimento já existe.");
            return false;
        }
    }
}

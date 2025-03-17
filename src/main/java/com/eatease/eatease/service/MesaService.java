package com.eatease.eatease.service;

import com.eatease.eatease.model.Mesa;
import com.eatease.eatease.repository.MesaRepository;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public boolean createMesa(int numero, boolean estadoLivre) {
        if (mesaRepository.findByNumero(numero).isEmpty()) {
            Mesa mesa = new Mesa();
            mesa.setNumero(numero);
            mesa.setEstadoLivre(estadoLivre);
            mesaRepository.save(mesa);
            System.err.println("Mesa adicionado com sucesso.");
            return true;
        }else{
            System.err.println("A mesa já existe.");
            return false;
        }
    }
}

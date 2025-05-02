package com.eatease.eatease.service;

import com.eatease.eatease.model.Mesa;
import com.eatease.eatease.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            System.err.println("Mesa adicionada com sucesso.");
            return true;
        } else {
            System.err.println("A mesa já existe.");
            return false;
        }
    }

    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> getMesaById(long id) {
        return mesaRepository.findById(id);
    }

    public Optional<Mesa> getMesaByNumero(int numero) {
        return mesaRepository.findByNumero(numero);
    }

    public boolean deleteMesa(long id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            System.err.println("Mesa removida com sucesso.");
            return true;
        } else {
            System.err.println("A mesa não existe.");
            return false;
        }
    }

    public boolean setMesaOcupada(long id) {
        Optional<Mesa> optMesa = mesaRepository.findById(id);
        if (optMesa.isPresent()) {
            Mesa mesa = optMesa.get();
            mesa.setEstadoLivre(false);
            mesaRepository.save(mesa);
            System.err.println("Mesa " + mesa.getNumero() + " definida como ocupada.");
            return true;
        } else {
            System.err.println("A mesa não existe.");
            return false;
        }
    }

    public boolean setMesaOcupadaByNumero(int numero) {
        Optional<Mesa> optMesa = mesaRepository.findByNumero(numero);
        if (optMesa.isPresent()) {
            Mesa mesa = optMesa.get();
            mesa.setEstadoLivre(false);
            mesaRepository.save(mesa);
            System.err.println("Mesa " + numero + " definida como ocupada.");
            return true;
        } else {
            System.err.println("A mesa não existe.");
            return false;
        }
    }

    public boolean setMesaLivre(long id) {
        Optional<Mesa> optMesa = mesaRepository.findById(id);
        if (optMesa.isPresent()) {
            Mesa mesa = optMesa.get();
            mesa.setEstadoLivre(true);
            mesaRepository.save(mesa);
            System.err.println("Mesa " + mesa.getNumero() + " definida como livre.");
            return true;
        } else {
            System.err.println("A mesa não existe.");
            return false;
        }
    }

    public boolean setMesaLivreByNumero(int numero) {
        Optional<Mesa> optMesa = mesaRepository.findByNumero(numero);
        if (optMesa.isPresent()) {
            Mesa mesa = optMesa.get();
            mesa.setEstadoLivre(true);
            mesaRepository.save(mesa);
            System.err.println("Mesa " + numero + " definida como livre.");
            return true;
        } else {
            System.err.println("A mesa não existe.");
            return false;
        }
    }
}

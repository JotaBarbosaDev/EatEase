package com.eatease.eatease.service;

import com.eatease.eatease.model.TipoMenu;
import com.eatease.eatease.repository.TipoMenuRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoMenuService {

    private final TipoMenuRepository tipoMenuRepository;

    public TipoMenuService(TipoMenuRepository tipoMenuRepository) {
        this.tipoMenuRepository = tipoMenuRepository;
    }

    public boolean createTipoMenu(String nome) {
        if (tipoMenuRepository.findByNome(nome).isEmpty()) {
            TipoMenu tipoMenu = new TipoMenu();
            tipoMenu.setNome(nome);
            tipoMenuRepository.save(tipoMenu);
            System.err.println("Tipo de menu adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O tipo de menu já existe.");
            return false;
        }
    }
}

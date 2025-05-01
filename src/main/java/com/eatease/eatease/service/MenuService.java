package com.eatease.eatease.service;

import com.eatease.eatease.model.Menu;
import com.eatease.eatease.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public String createMenu(String nome, String descricao, long[] items_id, long tipoMenu) {
        Menu menu = new Menu();
        menu.setNome(nome);
        menu.setDescricao(descricao);
        menu.setItems_id(items_id);
        menu.setTipoMenu(tipoMenu);

        if (menuRepository.findByNome(nome).isEmpty()) {
            menuRepository.save(menu);
            System.err.println("Menu adicionado com sucesso.");
            return null;
        } else {
            System.err.println("O menu já existe.");
            return "O menu já existe.";
        }

    }
}

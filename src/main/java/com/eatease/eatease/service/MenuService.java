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

    public boolean createMenu(long pratos_id[], long items_id[], long tipoMenu[]) {
        if (menuRepository.findByPratos(pratos_id).isEmpty()) {
            Menu menu = new Menu();
            menu.setPratos_id(pratos_id);
            menu.setItems_id(items_id);
            menu.setTipoMenu(tipoMenu);
            menuRepository.save(menu);
            System.err.println("Menu adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O menu já existe.");
            return false;
        }
    }
}

package com.eatease.eatease.service;

import com.eatease.eatease.model.Menu;
import com.eatease.eatease.repository.MenuRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ItemService itemService;
    private final TipoMenuService tipoMenuService;

    public MenuService(MenuRepository menuRepository,
            ItemService itemService,
            TipoMenuService tipoMenuService) {
        this.menuRepository = menuRepository;
        this.itemService = itemService;
        this.tipoMenuService = tipoMenuService;
    }

    public String createMenu(String nome, String descricao, long[] items_id, long tipoMenu) {
        Menu menu = new Menu();
        menu.setNome(nome);
        menu.setDescricao(descricao);
        menu.setItems_id(items_id);
        menu.setTipoMenu(tipoMenu);

        for (long itemId : items_id) {
            if (!itemService.doesItemExist(itemId)) {
                return "O item com ID " + itemId + " não existe.";
            }
        }
        if (!tipoMenuService.checkTipoMenuExists(tipoMenu)) {
            return "O tipo de prato com ID " + tipoMenu + " não existe.";
        }

        if (menuRepository.findByNome(nome).isEmpty()) {
            menuRepository.save(menu);
            System.err.println("Menu adicionado com sucesso.");
            return null;
        } else {
            System.err.println("O menu já existe.");
            return "O menu já existe.";
        }

    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public boolean checkMenuExists(Long id) {
        return menuRepository.existsById(id);
    }

    public boolean deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public String updateMenu(Long id, String nome, String descricao, long[] items_id, long tipoMenu) {
        if (!menuRepository.existsById(id)) {
            return "Menu não encontrado.";

        }
        for (long itemId : items_id) {
            if (!itemService.doesItemExist(itemId)) {
                return "O item com ID " + itemId + " não existe.";
            }
        }
        if (!tipoMenuService.checkTipoMenuExists(tipoMenu)) {
            return "O tipo de Menu com ID " + tipoMenu + " não existe.";
        }

        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu != null) {
            menu.setNome(nome);
            menu.setDescricao(descricao);
            menu.setItems_id(items_id);
            menu.setTipoMenu(tipoMenu);

            menuRepository.save(menu);
            return null;
        } else {
            return "Menu não encontrado.";
        }

    }
}

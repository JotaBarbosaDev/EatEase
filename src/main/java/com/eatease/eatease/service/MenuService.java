package com.eatease.eatease.service;

import com.eatease.eatease.model.Menu;
import com.eatease.eatease.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    /**
     * Cria um novo menu
     * 
     * @param nome       Nome do menu
     * @param descricao  Descrição do menu
     * @param itemsIds   Lista de IDs dos itens do menu
     * @param tipoMenuId ID do tipo de menu
     * @return null se bem-sucedido, mensagem de erro caso contrário
     */
    @Transactional
    public String createMenu(String nome, String descricao, List<Long> itemsIds, Long tipoMenuId) {
        // Validate input parameters
        if (!StringUtils.hasText(nome)) {
            return "O nome do menu não pode estar vazio.";
        }

        if (itemsIds == null) {
            return "A lista de itens não pode ser nula.";
        }

        if (itemsIds.isEmpty()) {
            return "O menu deve conter pelo menos um item.";
        }

        if (menuRepository.existsByNome(nome)) {
            return "O menu com o nome '" + nome + "' já existe.";
        }

        // Validate item IDs existence
        for (Long itemId : itemsIds) {
            if (itemId == null) {
                return "ID de item nulo encontrado na lista.";
            }

            if (!itemService.doesItemExist(itemId)) {
                return "O item com ID " + itemId + " não existe.";
            }
        }

        // Validate tipo menu ID
        if (tipoMenuId == null) {
            return "O ID do tipo de menu não pode ser nulo.";
        }

        if (!tipoMenuService.checkTipoMenuExists(tipoMenuId)) {
            return "O tipo de menu com ID " + tipoMenuId + " não existe.";
        }

        Menu menu = new Menu();
        menu.setNome(nome);
        menu.setDescricao(descricao);

        // Convert List<Long> to long[] for the Menu entity
        long[] itemsArray = itemsIds.stream().mapToLong(Long::longValue).toArray();
        menu.setItems_id(itemsArray);
        menu.setTipoMenu(tipoMenuId);
        menuRepository.save(menu);
        return null;
    }

    /**
     * Obtém todos os menus
     * 
     * @return Lista de todos os menus
     */
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    /**
     * Verifica se um menu existe pelo seu ID
     * 
     * @param id ID do menu
     * @return true se o menu existe, false caso contrário
     */
    public boolean checkMenuExists(Long id) {
        if (id == null) {
            return false;
        }
        return menuRepository.existsById(id);
    }

    /**
     * Obtém um menu pelo seu ID
     * 
     * @param id ID do menu
     * @return Menu encontrado ou empty se não existir
     */
    public Optional<Menu> getMenuById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return menuRepository.findById(id);
    }

    /**
     * Exclui um menu pelo seu ID
     * 
     * @param id ID do menu a ser excluído
     * @return true se o menu foi excluído, false caso contrário
     */
    @Transactional
    public boolean deleteMenu(Long id) {
        if (id == null) {
            return false;
        }

        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Atualiza um menu existente
     * 
     * @param id         ID do menu a ser atualizado
     * @param nome       Novo nome do menu
     * @param descricao  Nova descrição do menu
     * @param itemsIds   Nova lista de IDs dos itens do menu
     * @param tipoMenuId Novo ID do tipo de menu
     * @return null se bem-sucedido, mensagem de erro caso contrário
     */
    @Transactional
    public String updateMenu(Long id, String nome, String descricao, List<Long> itemsIds, Long tipoMenuId) {
        // Validate input parameters
        if (id == null) {
            return "O ID do menu não pode ser nulo.";
        }

        if (!StringUtils.hasText(nome)) {
            return "O nome do menu não pode estar vazio.";
        }

        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isEmpty()) {
            return "Menu com ID " + id + " não encontrado.";
        }

        Menu menu = menuOptional.get();

        // Check if the new name conflicts with existing menus (excluding the current
        // menu)
        if (!nome.equals(menu.getNome()) && menuRepository.existsByNome(nome)) {
            return "Já existe outro menu com o nome '" + nome + "'.";
        }

        if (itemsIds == null) {
            return "A lista de itens não pode ser nula.";
        }

        if (itemsIds.isEmpty()) {
            return "O menu deve conter pelo menos um item.";
        }

        // Validate item IDs existence
        for (Long itemId : itemsIds) {
            if (itemId == null) {
                return "ID de item nulo encontrado na lista.";
            }

            if (!itemService.doesItemExist(itemId)) {
                return "O item com ID " + itemId + " não existe.";
            }
        }

        // Validate tipo menu ID
        if (tipoMenuId == null) {
            return "O ID do tipo de menu não pode ser nulo.";
        }

        if (!tipoMenuService.checkTipoMenuExists(tipoMenuId)) {
            return "O tipo de menu com ID " + tipoMenuId + " não existe.";
        }

        menu.setNome(nome);
        menu.setDescricao(descricao);

        // Convert List<Long> to long[] for the Menu entity
        long[] itemsArray = itemsIds.stream().mapToLong(Long::longValue).toArray();
        menu.setItems_id(itemsArray);
        menu.setTipoMenu(tipoMenuId);

        menuRepository.save(menu);
        return null;
    }
}

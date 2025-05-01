package com.eatease.eatease.controller;

import com.eatease.eatease.service.*;

public class MenuController {

    private final MenuService menuService;
    private final IngredientesService ingredientesService;
    private final TipoPratoService tipoPratoService;
    private final TipoMenuService tipoMenuService;

    public MenuController(MenuService menuService, IngredientesService ingredientesService,
            TipoPratoService tipoPratoService, TipoMenuService tipoMenuService) {
        this.menuService = menuService;
        this.ingredientesService = ingredientesService;
        this.tipoPratoService = tipoPratoService;
        this.tipoMenuService = tipoMenuService;
    }
}

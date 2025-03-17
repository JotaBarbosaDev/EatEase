package com.eatease.eatease.service;

import com.eatease.eatease.model.Item;
import com.eatease.eatease.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public boolean createItem(String nome, long tipoPrato_id, float preco, long ingredientes_id[], boolean eCpmposto, int stockAtual) {
        if (itemRepository.findByNome(nome).isEmpty()) {
            Item item = new Item();
            item.setNome(nome);
            item.setTipoPrato_id(tipoPrato_id);
            item.setPreco(preco);
            item.setIngrediente_id(ingredientes_id);
            item.seteCpmposto(eCpmposto);
            item.setStockAtual(stockAtual);
            itemRepository.save(item);
            System.err.println("Item adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O item já existe.");
            return false;
        }
    }
}

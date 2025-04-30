package com.eatease.eatease.service;

import com.eatease.eatease.model.Item;
import com.eatease.eatease.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public boolean createItem(String nome, long tipoPrato_id, float preco, long ingredientes_id[], boolean eCpmposto,
            int stockAtual) {
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
        } else {
            System.err.println("O item já existe.");
            return false;
        }
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<List<Item>> getByPratoId(long tipoPratoId) {
        return itemRepository.findByTipoPratoId(tipoPratoId);
    }

    public Optional<Item> editItem(long id, String nome, long tipoPrato_id, float preco, long ingredientes_id[],
            boolean eCpmposto,
            int stockAtual) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setNome(nome);
            item.setTipoPrato_id(tipoPrato_id);
            item.setPreco(preco);
            item.setIngrediente_id(ingredientes_id);
            item.seteCpmposto(eCpmposto);
            item.setStockAtual(stockAtual);
            item = itemRepository.save(item);
            System.err.println("Item editado com sucesso.");
            return Optional.of(item);
        } else {
            System.err.println("O item não existe.");
            return Optional.empty();
        }
    }

    public boolean deleteItem(long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            System.err.println("Item eliminado com sucesso.");
            return true;
        } else {
            System.err.println("O item não existe.");
            return false;
        }
    }
}

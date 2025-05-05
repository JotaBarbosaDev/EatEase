package com.eatease.eatease.service;

import com.eatease.eatease.dto.IngredienteQuantDTO;
import com.eatease.eatease.model.Item;
import com.eatease.eatease.repository.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper; // Jackson
    private final IngredientesService ingredientesService;
    private final TipoPratoService tipoPratoService;

    public ItemService(ItemRepository itemRepository, ObjectMapper objectMapper,
            IngredientesService ingredientesService, TipoPratoService tipoPratoService) {
        this.itemRepository = itemRepository;
        this.objectMapper = objectMapper;
        this.ingredientesService = ingredientesService;
        this.tipoPratoService = tipoPratoService;
    }

    /* ---------------------------- CREATE ------------------------------ */
    public Item createItem(String nome,
            long tipoPratoId,
            float preco,
            List<IngredienteQuantDTO> ingredientes, // <-- alterado
            boolean eComposto,
            int stockAtual) throws Exception {

        if (itemRepository.findByNome(nome).isPresent()) {
            System.err.println("O item já existe.");
            throw new IllegalArgumentException("O item já existe.");
        }

        for (IngredienteQuantDTO ingrediente : ingredientes) {
            if (!ingredientesService.doesIngredienteExist(ingrediente.getIngredienteId())) {
                System.err.println("O ingrediente com ID " + ingrediente.getIngredienteId() + " não existe.");
                throw new IllegalArgumentException(
                        "O ingrediente com ID " + ingrediente.getIngredienteId() + " não existe.");
            }
        }

        if (!tipoPratoService.checkTipoPratoExists(tipoPratoId)) {
            System.err.println("O tipo de prato com ID " + tipoPratoId + " não existe.");
            throw new IllegalArgumentException("O tipo de prato com ID " + tipoPratoId + " não existe.");
        }

        Item item = new Item();
        item.setNome(nome);
        item.setTipoPrato_id(tipoPratoId);
        item.setPreco(preco);
        item.seteCpmposto(eComposto);
        item.setStockAtual(stockAtual);

        try {
            String json = objectMapper.writeValueAsString(ingredientes);
            item.setIngredientesJson(json);
        } catch (JsonProcessingException e) {
            // erro de serialização — não grava
            System.err.println("Falha a serializar ingredientes: " + e.getMessage());
            throw new Exception("Falha a serializar ingredientes: " + e.getMessage());
        }

        itemRepository.save(item);
        System.err.println("Item adicionado com sucesso.");
        return item; // sucesso
    }

    /* ---------------------------- READ ------------------------------- */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getByPratoId(long tipoPratoId) {
        return itemRepository.findByTipoPratoId(tipoPratoId);
    }

    /* ---------------------------- UPDATE ----------------------------- */
    public Item editItem(long id,
            String nome,
            long tipoPratoId,
            float preco,
            List<IngredienteQuantDTO> ingredientes, // <-- alterado
            boolean eComposto,
            int stockAtual) throws Exception {

        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isEmpty()) {
            System.err.println("O item não existe.");
            throw new IllegalArgumentException("O item não existe.");
        }

        for (IngredienteQuantDTO ingrediente : ingredientes) {
            if (!ingredientesService.doesIngredienteExist(ingrediente.getIngredienteId())) {
                System.err.println("O ingrediente com ID " + ingrediente.getIngredienteId() + " não existe.");
                throw new IllegalArgumentException(
                        "O ingrediente com ID " + ingrediente.getIngredienteId() + " não existe.");
            }
        }

        if (!tipoPratoService.checkTipoPratoExists(tipoPratoId)) {
            System.err.println("O tipo de prato com ID " + tipoPratoId + " não existe.");
            throw new IllegalArgumentException("O tipo de prato com ID " + tipoPratoId + " não existe.");
        }

        Item item = itemOpt.get();
        item.setNome(nome);
        item.setTipoPrato_id(tipoPratoId);
        item.setPreco(preco);
        item.seteCpmposto(eComposto);
        item.setStockAtual(stockAtual);

        try {
            String json = objectMapper.writeValueAsString(ingredientes);
            item.setIngredientesJson(json);
        } catch (JsonProcessingException e) {
            System.err.println("Falha a serializar ingredientes: " + e.getMessage());
            throw new Exception("Falha a serializar ingredientes: " + e.getMessage());
        }

        itemRepository.save(item);
        System.err.println("Item editado com sucesso.");
        return item; // sucesso
    }

    /* ---------------------------- DELETE ----------------------------- */
    public boolean deleteItem(long id) {
        if (!itemRepository.existsById(id)) {
            System.err.println("O item não existe.");
            return false;
        }
        itemRepository.deleteById(id);
        System.err.println("Item eliminado com sucesso.");
        return true;
    }

    public Optional<Item> getItemById(long id) {
        return itemRepository.findById(id);
    }

    public List<IngredienteQuantDTO> getIngredientesByItemId(long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            String json = item.getIngredientesJson();
            try {
                return objectMapper.readValue(json,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, IngredienteQuantDTO.class));
            } catch (JsonProcessingException e) {
                System.err.println("Falha a deserializar ingredientes: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean doesItemExist(long id) {
        return itemRepository.existsById(id);
    }

    public Item getById(long id) {
        if (itemRepository.existsById(id)) {
            return itemRepository.findById(id).get();
        }
        return null;
    }
}

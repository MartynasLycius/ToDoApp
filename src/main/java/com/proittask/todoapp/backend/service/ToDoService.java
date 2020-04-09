package com.proittask.todoapp.backend.service;

import com.proittask.todoapp.backend.entity.Item;
import com.proittask.todoapp.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ToDoService {
    private static final Logger LOGGER = Logger.getLogger(ToDoService.class.getName());
    private ItemRepository itemRepository;

    @Autowired
    public ToDoService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void save(Item item) {
        if (item == null) {
            LOGGER.log(Level.SEVERE,
                    "Item is null. Are you sure you have connected your form to the application?");
            return;
        }
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return itemRepository.findAll();
        } else {
            return itemRepository.search(filterText);
        }
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public long count() {
        return itemRepository.count();
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(items ->
                stats.put(items.getStatus().toString(), items.getId().intValue()));
        return stats;
    }

    @Transactional
    @PostConstruct
    public void populateTestData() {
        if (itemRepository.count() == 0) {
            Random r = new Random(0);
            List<Item> companies = itemRepository.findAll();
            itemRepository.saveAll(
                    Stream.of("Important", "Emergency", "Deadline")
                            .map(name -> {
                                Item item = new Item();
                                item.setName(name);
                                item.setDescription(" ");
                                LocalDate today = LocalDate.now();
                                item.setTargetDate(today);
                                item.setStatus(Item.Status.values()[r.nextInt(Item.Status.values().length)]);
                                return item;
                            }).collect(Collectors.toList()));
        }
    }
}

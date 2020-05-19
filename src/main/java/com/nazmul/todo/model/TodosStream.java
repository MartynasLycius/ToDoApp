/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.model;

import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple app data specific stream class to make code more object oriented.
 * @author nazmul
 */
public class TodosStream implements ForwardingStream<TodoItem>{
    private Stream<TodoItem> stream;

    private TodosStream(Stream<TodoItem> stream) {
        this.stream = stream;
    }
    
    @Override
    public Stream<TodoItem> getStream() { return stream; }
    
    /**
     * Gets Stream with the given list
     * @param todos list of todo item
     * @return Stream
     */
    public static TodosStream of(List<TodoItem> todos) {
        return new TodosStream(todos.stream());
    }

    /**
     * Filters those todos which are completed
     * @return Stream 
     */
    public TodosStream completed() {
        return new TodosStream(this.getStream()
                .filter(TodoItem::getDone));
    }

    /**
     * Gets all todos from the Stream
     * @return list of {@see com.nazmul.todo.domain.TodoItem}.
     */
    public List<TodoItem> todos() {
        return this.getStream().collect(Collectors.toList());
    }

    /**
     * Gets number of total todos by each Category
     * @return a Map
     */
    public Map<Category, Long> groupByCategory() {
        return this.getStream().collect(Collectors.groupingBy(TodoItem::getCategory,
                        Collectors.counting()));
    }

    /**
     * Gets total count of todo items in the stream
     * @return num of todo items.
     */
    public Long total() {
        return this.getStream()
                .count();
    }

    /**
     * Gets all categories which includes any of the given allowed categories id.
     * @param allowedCategoryIds list of allowed categories id.
     * @return Stream
     */
    public TodosStream ofCategories(List<Long> allowedCategoryIds) {
        return new TodosStream(this.getStream()
                .filter(
                        (t) -> allowedCategoryIds.contains(t.getCategory().getId())
                ));
    }

    /**
     * Filtered the stream by the given filter
     * @param filter the given filter
     * @return Stream
     */
    public TodosStream filterBy(Predicate<TodoItem> filter) {
        return new TodosStream(
                this.getStream()
                .filter(filter)
        );
    }

    /**
     * Gets todo items count by each date from the stream
     * @return a map with date as the key and num of todo item as the count.
     */
    public Map<LocalDate, Long> countByDate() {
        Map<LocalDate, Long> collect = this.getStream()
                .collect(Collectors.groupingBy(TodoItem::getTimecreated,
                        Collectors.counting()));
        
        return collect;
    }

    /**
     * Gets todo items count by each date of the given dates from stream.
     * @param givenDates
     * @return a map with date as the key and num of todo item as the count.
     */
    public Map<LocalDate, Long> countByGivenDates(List<LocalDate> givenDates) {
        Map<LocalDate, Long> countByGiventDates = new HashMap<>();
        Map<LocalDate, Long> collect = countByDate();
        
        for(LocalDate d : givenDates) {
            Long count = collect.get(d);
            countByGiventDates.put(d, count == null ? 0 : count);
        }
        
        return countByGiventDates;
    }

    /**
     * Sort all todos of the stream by its category id 
     * To see the order chech {@see com.nazmul.constant.CategoryType}
     * @return Sorted Stream
     */
    public TodosStream orderByCategoryId() {
        return new TodosStream(
                this.getStream()
                .sorted((t1, t2) -> {
                            return t1.getCategory().compareTo(t2.getCategory());
                        })
        );
    }
    
}

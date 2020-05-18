/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.model;

import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple app data specific stream class to make code more object oriented.
 * @author nazmul
 */
public class CategoryStream  implements ForwardingStream<Category>{
    private Stream<Category> stream;
    
    @Override
    public Stream<Category> getStream() { return stream; }

    private CategoryStream(Stream<Category> stream) {
        this.stream = stream;
    }
  
    /**
     * Gets Stream with the given list
     * @param categories list of {@see com.nazmul.todo.domain.Category} item
     * @return Stream
     */
    public static CategoryStream of(List<Category> categories) {
        return new CategoryStream(categories.stream());
    }

    /**
     * Converts all grouped category to a helper medel
     * @param todos list of todo item of which categories are grouped
     * @return list of category counter helper models {@see com.nazmul.todo.model.CategoryCounterModel}
     */
    public List<CategoryCounterModel> convertToCategoryCounterModel(List<TodoItem> todos) {

        Map<Category, Long> categoryCompletedCounter = TodosStream.of(todos).completed().groupByCategory();
        Map<Category, Long> categoryTotalCounter = TodosStream.of(todos).groupByCategory();
        
        //add category type 'ALL' because its not a persisted data
        Stream<CategoryCounterModel> categoryCounterStream = this.getStream()
                .map(cat -> {
                    return (CategoryType.All.getId().equals(cat.getId()))
                            ? new CategoryCounterModel(
                                    CategoryType.findById(cat.getId()),
                                    TodosStream.of(todos).total(),
                                    TodosStream.of(todos).completed().total()
                            )
                            : new CategoryCounterModel(
                                    CategoryType.findById(cat.getId()),
                                    categoryTotalCounter.get(cat),
                                    categoryCompletedCounter.get(cat)
                            );
                });
        

        /**
         * Kept the following code as alternative approach  to do this method functionality
         */
//        Stream<CategoryCounterModel> categoryCounterStream = this.getStream()
//                .map(cat -> {
//                    //Category All isn't stored in db its manually created on runtime (to filter all todos )
//                    //Thats why its wont have any ref to TodoItems. For that All type
//                    //we need to consider all todos which is passed in this method params
//                    List<TodoItem> todoItems = (CategoryType.All.getId().equals(cat.getId()))
//                                                ? todos : cat.getTodoItemCollection();
//                    
//                    Long totalCompleted = TodosStream.of(todoItems).completed().total();
//                    Long total = TodosStream.of(todoItems).total();
//                    return new CategoryCounterModel(
//                            CategoryType.findById(cat.getId()),
//                            totalCompleted,
//                            total
//                    );
//                });
        //Now manually generate AlL category type
//        Stream<CategoryCounterModel> all = Stream.of(new CategoryCounterModel(
//                CategoryType.All,
//                TodosStream.of(todos).total(),
//                TodosStream.of(todos).completed().total()
//        ));
        
        return categoryCounterStream
                .sorted(Comparator.comparingLong( c -> c.getType().getId()))
                .collect(Collectors.toList());
    }

    /**
     * Adds category type 'ALL' {@see com.nazmul.todo.CategoryType.AlL} to the stream
     * @return Stream
     */
    public CategoryStream addCategoryTypeAll() {
        return new CategoryStream(
            Stream.concat(
                this.getStream(),
                Stream.of(Category.typeAll())
            )
        );
    }

    public CategoryStream orderedById() {
        return new CategoryStream(this.getStream()
                .sorted()
        );
    }
}

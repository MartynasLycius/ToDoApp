/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.chart.view;

import com.nazmul.todo.MainUI;
import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import com.nazmul.todo.facade.TodoItemFacade;
import com.nazmul.todo.model.TodosStream;
import com.nazmul.todo.service.NavigationEvent;
import com.nazmul.todo.util.Util;
import com.vaadin.cdi.UIScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author nazmul
 */
@UIScoped
public class ChartDataPresenter implements IChartDataPresenter{
    
    @Inject
    private TodoItemFacade itemFacade;
    
    @Inject
    private javax.enterprise.event.Event<NavigationEvent> navigationEvent;
    
    private List<TodoItem> todos;
    
    @PostConstruct
    public void intit() {
        LocalDate now = MainUI.getInstance().getSelectedDate();
        todos = itemFacade.getTodosBetweenDates(now, 8);
    }
    
    @Override
    public List<Double> getCountOfTodosByDateOnBasedOnFilter(Predicate<TodoItem> filter, LocalDate startDate, int pastNumOfDays) {
        List<Double> countByDate = getCountOfTodosByDateWithCompletionFilter(filter, startDate, pastNumOfDays);
        
        return countByDate;
    }

    private List<Double> getCountOfTodosByDateWithCompletionFilter(Predicate<TodoItem> filter, LocalDate startDate, int pastNumOfDays) {
       
        List<LocalDate> pastDaysDate = Util.getPastDaysDates(startDate, pastNumOfDays);
        Map<LocalDate, Long> countByDate = TodosStream.of(todos).filterBy(filter).countByGivenDates(pastDaysDate);
        
        //Sort the map by date
        TreeMap<LocalDate, Long> sortByDate = new TreeMap<>(countByDate);
        
        //Now only gets the counts list
        return sortByDate.values()
                .stream()
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public String[] getFormattedPastDaysDates(LocalDate starDate, int pastNumDays) {
        String[] dates = formatPastDaysDates(starDate, pastNumDays)
                .stream()
                .toArray(String[]::new);
        return dates;
    }

    private List<String> formatPastDaysDates(LocalDate starDate, int pastNumDays) {
        return Util.getPastDaysDates(starDate, pastNumDays)
                .stream()
                .map((t) -> {
                    return Util.getChartFormattedDate(t); //To change body of generated lambdas, choose Tools | Templates.
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getCountOfTodosByCategories(Predicate<TodoItem> filter) {
        TreeMap<Category, Long> sortByCategory = getCountBycategories(filter);
        
        //Now gets the list of counter 
        return sortByCategory.values()
                .stream()
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public String[] getCategoriesLabel(Predicate<TodoItem> filter) {
        TreeMap<Category, Long> sortByCategory = getCountBycategories(filter);
        
        //Now gets the categories' name
        return sortByCategory.keySet()
                .stream()
                .map((t) -> {
                    return t.getName().toUpperCase(); 
                })
                .toArray(String[]::new);
    }
    
    private TreeMap<Category, Long> getCountBycategories(Predicate<TodoItem> filter) {
        Map<Category, Long> groupByCategory = TodosStream.of(todos).filterBy(filter).groupByCategory();
        
        TreeMap<Category, Long> sortByCategory = new TreeMap<>(groupByCategory);
        
        return sortByCategory;
    }

    @Override
    public void navigateTo(String viewId) {
        navigationEvent.fire(new NavigationEvent(viewId));
    }
}

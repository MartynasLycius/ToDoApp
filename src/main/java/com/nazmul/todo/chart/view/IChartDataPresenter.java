/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.chart.view;

import com.nazmul.todo.domain.TodoItem;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author nazmul
 */
public interface IChartDataPresenter {
    /**
     * Gets all the dates in string from given date to given past no of days
     * @param starDate
     * @param pastNumDays
     * @return Array of date in Sting format
     */
    public String[] getFormattedPastDaysDates(LocalDate starDate, int pastNumDays);
    
    /**
     * Gets filtered todos counter for each date from given date to given past no of days
     * @param filter
     * @param startDate
     * @param pastNumOfDays
     * @return list of counts of todos.
     */
    public List<Double> getCountOfTodosByDateOnBasedOnFilter(Predicate<TodoItem> filter, LocalDate startDate, int pastNumOfDays);
    
    /**
     * Gets filtered todos counter for each categories
     * @param filter 
     * @return list of counts of todos.
     */
    public List<Double> getCountOfTodosByCategories(Predicate<TodoItem> filter);

    public void navigateTo(String viewId);

    /**
     * Gets filtered categories' name
     * @param filter
     * @return array of String of categories' name
     */
    public String[] getCategoriesLabel(Predicate<TodoItem> filter);
}

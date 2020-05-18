/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.model;

import com.nazmul.todo.constant.CategoryType;

/**
 * A helper value object class to represent Category type and todos counter.
 * @author nazmul
 */
public class CategoryCounterModel {
    private CategoryType type;
    
    private final Long total;
    private final Long completedCount;
    private final Long uncompletedCount;

    public CategoryCounterModel(CategoryType type, Long total, Long completedCount) {
        this.type = type;
        this.total = (total == null ? 0l : total);
        this.completedCount = (completedCount == null ? 0l : completedCount);
        this.uncompletedCount = (this.total - this.completedCount);
    }

    public CategoryType getType() {
        return type;
    }

    public Long getTotal() {
        return total;
    }

    public Long getCompletedCount() {
        return completedCount;
    }

    public Long getUncompletedCount() {
        return uncompletedCount;
    }
    
}

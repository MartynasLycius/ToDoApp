package com.proit.todo.core.Form;


import com.proit.todo.core.config.PaginationConfig;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class PaginationForm {

    public PaginationForm() {
        this.pageSize = PaginationConfig._DEFAULT_PAGE_SIZE;
        this.pageNumber = PaginationConfig._DEFAULT_PAGE_NUMBER;
        this.sort = null;
    }


    public PaginationForm(Integer pageSize, Integer pageNumber, String sort) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sort = sort;
    }

    @Min(value = 1, message = "Page size must greater then 0")
    @Max(value = PaginationConfig._MAX_PAGE_SIZE, message = "Page size max limit "+PaginationConfig._MAX_PAGE_SIZE)
    private Integer pageSize;

    @Min(value = 1, message = "Page number must greater then 1")
    private Integer pageNumber;

    private String sort;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}


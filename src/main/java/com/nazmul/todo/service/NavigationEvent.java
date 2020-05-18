/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.service;

/**
 * Simple value object class for navigation event.
 * @author nazmul
 */
public class NavigationEvent {
    private final String navigateTo;
    private final Long itemId;

    public NavigationEvent(String navigateTo) {
        this.navigateTo = navigateTo;
        itemId = null;
    }

    public NavigationEvent(String navigateTo, Long itemId) {
        this.navigateTo = navigateTo;
        this.itemId = itemId;
    }

    public String getNavigateTo() {
        return navigateTo;
    }

    public Long getItemId() {
        return itemId;
    }

}

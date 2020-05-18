/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.service;

import java.io.Serializable;

import javax.enterprise.event.Observes;

public interface NavigationService extends Serializable {

    /**
     * Observes the navigation event which fired by the navigator
     * @param event 
     */
    public void onNavigationEvent(@Observes NavigationEvent event);
}

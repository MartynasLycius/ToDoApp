package com.proit.todo.ui.helper;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import org.apache.commons.lang3.StringUtils;

public class TaskFormValidation {
    public static boolean validateName(String name,boolean showDialod){
        boolean flag =StringUtils.isBlank(name);

        if(flag && showDialod){
            showDialog("Task name required");
        }
        return (!flag);
    }
    public static void showDialog(String msg){
        Dialog dialog = new Dialog();
        dialog.add(new Label("Task name required"));

        dialog.setWidth("200px");
        dialog.setHeight("50px");
        dialog.open();
    }
}

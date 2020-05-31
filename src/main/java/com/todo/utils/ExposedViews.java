package com.todo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ExposedViews {
    /*HOME("/"),*/
    LOGIN("/login"),
    LOGOUT("/logout"),
    REGISTRATION("/registration");

    private String uri;
    ExposedViews(String uri){
        this.uri=uri;
    }

    public String getUri(){return uri;}

    public static List<ExposedViews> getAll(){
        return Arrays.asList(ExposedViews.values());
    }

    public static List<String> getAllUriList(){
        List<String> uriList= new ArrayList<>();
        for (ExposedViews exposedViews: ExposedViews.values()){
            uriList.add(exposedViews.getUri());
        }
        return uriList;
    }
}

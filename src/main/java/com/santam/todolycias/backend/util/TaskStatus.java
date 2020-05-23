package com.santam.todolycias.backend.util;

public enum TaskStatus
{
    S0("Neutral"), S1("Progress"), S2("Hold"), S3("Rejected"), S4("Done");

    private String action;

    public  String getAction()
    {
        return this.action;
    }

    TaskStatus(String action)
    { 
        this.action = action; 
    } 
} 
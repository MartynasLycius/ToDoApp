package com.proit.todo.core.constant;

public class Enums {
    public enum TASK_STATE{
        NEW,DONE;


        public static Enums.TASK_STATE getState(String name){
            TASK_STATE targetState = null;
            for(Enums.TASK_STATE state :Enums.TASK_STATE.values()){
                if(state.name().equals(name)){
                    targetState = state;
                    break;
                }
            }
            return targetState;
        }
    }

}

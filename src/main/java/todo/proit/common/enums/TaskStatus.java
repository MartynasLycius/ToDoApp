package todo.proit.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@AllArgsConstructor
@Getter
public enum TaskStatus {
    ALL(2, "All"),
    PENDING(0, "Pending"),
    DONE(1, "Done");


    private int code;
    private String value;

    public static String[] getAllValueAsArray(){
         return Arrays
                 .stream(TaskStatus.values())
                 .map(TaskStatus::getValue)
                 .toArray(String[]::new);
    }

    public static String getValueByCode(int code){
        for(TaskStatus taskStatus : TaskStatus.values()){
            if(taskStatus.getCode() == code)
                return taskStatus.getValue();
        }
        return "";
    }

    public static int getCodeByValue(String value){
        for(TaskStatus taskStatus : TaskStatus.values()){
            if(taskStatus.getValue().equalsIgnoreCase(value))
                return taskStatus.getCode();
        }
        return -1;
    }

    public static boolean isValid(int code){
        for(TaskStatus taskStatus : TaskStatus.values()){
            if(taskStatus.getCode() == code)
                return true;
        }
        return false;
    }

    public static boolean isNoAll(int code){
        return (TaskStatus.ALL.getCode() != code);
    }

    public static boolean isDone(String value){
        return TaskStatus.DONE.getValue().equalsIgnoreCase(value);
    }
}

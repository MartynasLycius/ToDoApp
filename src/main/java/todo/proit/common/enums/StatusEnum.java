package todo.proit.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {
    PENDING(0, "Pending"),
    DONE(1, "Done"),
    ALL(2, "All");

    private int code;
    private String value;

    public static String getValueByCode(int code){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getCode() == code)
                return statusEnum.getValue();
        }
        return "";
    }

    public static int getCodeByValue(String value){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getValue().equalsIgnoreCase(value))
                return statusEnum.getCode();
        }
        return -1;
    }

    public static boolean isValid(int code){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getCode() == code)
                return true;
        }
        return false;
    }

    public static boolean isNoAll(int code){
        return (StatusEnum.ALL.getCode() != code);
    }
}

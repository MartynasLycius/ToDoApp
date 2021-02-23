package todo.proit.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dipanjal
 * @since 2/19/2021
 */
@Getter
@AllArgsConstructor
public enum DirectionEnum {
    ASC,
    DESC;

    public static boolean isAscending(String code){
        return DirectionEnum.ASC.name().equalsIgnoreCase(code);
    }

    public static boolean isDescending(String code){
        return DirectionEnum.DESC.name().equalsIgnoreCase(code);
    }
}

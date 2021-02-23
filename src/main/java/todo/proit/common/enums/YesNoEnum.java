package todo.proit.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@AllArgsConstructor
@Getter
public enum YesNoEnum {
    YES('Y'),
    NO('N');

    private char code;
}

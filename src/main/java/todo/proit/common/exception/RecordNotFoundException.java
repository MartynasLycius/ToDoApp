package todo.proit.common.exception;

import lombok.NoArgsConstructor;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}

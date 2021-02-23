package todo.proit.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private Errors errors;
}

package todo.proit.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    private long id;
    private String name;
    private String status;
}

package todo.proit.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailDto extends TaskDto {
    private String description;
    private String createdAt;
}

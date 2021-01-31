package com.hafiz.interview.east.netic.todo.dataclass.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hafiz.interview.east.netic.todo.core.crud.IdHolder;
import com.hafiz.interview.east.netic.todo.dataclass.common.UserDto;
import com.hafiz.interview.east.netic.todo.validatorgroup.CreateValidatorGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TodoItemDTO extends IdHolder {
  @NotNull(groups = CreateValidatorGroup.class, message = "User id can't be blank")
  private UUID userId;
  private UserDto user;
  @NotBlank(groups = CreateValidatorGroup.class, message = "Title can't be blank")
  private String title;
  @NotBlank(groups = CreateValidatorGroup.class, message = "Description can't be blank")
  private String description;
  @NotNull(groups = CreateValidatorGroup.class, message = "Date can't be blank")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime date;
}

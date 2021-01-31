package com.hafiz.interview.east.netic.todo.dataclass.common;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
  private UUID id;
  private String username;
  private String fullName;
}

package com.hafiz.interview.east.netic.auth.dataclass;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDTO {
  @NotBlank(message = "Name can't be blank")
  private String username;
  @NotBlank(message = "Password can't be blank")
  private StringBuilder password;
  @NotBlank(message = "User name can't be blank")
  private String fullName;
}

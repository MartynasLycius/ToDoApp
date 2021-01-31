package com.hafiz.interview.east.netic.auth.dataclass;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
  private UUID id;
  private String username;
  private String fullName;
}

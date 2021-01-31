package com.hafiz.interview.east.netic.auth.controllers;

import com.hafiz.interview.east.netic.auth.core.constants.EndPoint;
import com.hafiz.interview.east.netic.auth.dataclass.UserCreateDTO;
import com.hafiz.interview.east.netic.auth.dataclass.UserResponseDTO;
import com.hafiz.interview.east.netic.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(EndPoint.USER_URL)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<UserResponseDTO> getList() {
    return userService.getList();
  }

  @GetMapping("/{username}")
  public UserResponseDTO getByUsername(@PathVariable String username) {
    return userService.getByUserName(username);
  }

  //TODO handle request body validation correctly
  @PostMapping(EndPoint.LIST_BY_ID_SET_URL)
  public List<UserResponseDTO> getListByIdSet(@RequestBody @Valid @NotEmpty Set<@NotBlank UUID> idSet) {
    return userService.getListByIdSet(idSet);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public UserResponseDTO create(@RequestBody @Valid UserCreateDTO user) {
    return userService.create(user);
  }

}

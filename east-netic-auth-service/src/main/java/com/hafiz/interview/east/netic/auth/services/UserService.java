package com.hafiz.interview.east.netic.auth.services;

import com.hafiz.interview.east.netic.auth.dataclass.UserCreateDTO;
import com.hafiz.interview.east.netic.auth.dataclass.UserResponseDTO;
import com.hafiz.interview.east.netic.auth.entities.User;
import com.hafiz.interview.east.netic.auth.exceptions.ExceptionHolders;
import com.hafiz.interview.east.netic.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder passwordEncoder;

  public List<UserResponseDTO> getList() {
    return repository.findAll()
        .stream()
        .map(u -> modelMapper.map(u, UserResponseDTO.class))
        .collect(Collectors.toList());
  }

  public UserResponseDTO create(UserCreateDTO user) {
    this.validate(user);
    User entity = modelMapper.map(user, User.class);
    entity.setPassword(passwordEncoder.encode(user.getPassword()));
    return modelMapper.map(repository.save(entity), UserResponseDTO.class);
  }

  private void validate(UserCreateDTO user) {
    User entity = repository.findByUsername(user.getUsername());
    if(entity != null) throw new ExceptionHolders.InvalidRequestException("User name already exists");
  }

  public List<UserResponseDTO> getListByIdSet(Set<UUID> idSet) {
    return repository.findAllByIdIn(idSet)
        .stream()
        .map(u -> modelMapper.map(u, UserResponseDTO.class))
        .collect(Collectors.toList());
  }

  public UserResponseDTO getByUserName(String username) {
    return modelMapper.map(repository.findByUsername(username), UserResponseDTO.class);
  }
}

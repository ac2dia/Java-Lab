package com.example.usecasewithmapstruct.domain.user.service;

import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.domain.role.repository.RoleRepository;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.entity.User;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import com.example.usecasewithmapstruct.domain.user.mapper.user.UserCreateMapper;
import com.example.usecasewithmapstruct.domain.user.mapper.user.UserResponseMapper;
import com.example.usecasewithmapstruct.domain.user.mapper.userrole.UserRoleResponseMapper;
import com.example.usecasewithmapstruct.domain.user.repository.UserRepository;
import com.example.usecasewithmapstruct.domain.user.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final RoleRepository roleRepository;

  @Override
  public List<UserResponseDto> getUsers() {
    List<User> userList = userRepository.findAll();
    if (userList == null) {
      return new ArrayList<>();
    }

    return UserResponseMapper.INSTANCE.toDtoList(userList);
  }

  @Override
  public UserResponseDto getUserById(String id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new NoSuchElementException("Failed to find user by id {" + id + "}");
    }

    return UserResponseMapper.INSTANCE.toDto(user);
  }

  @Override
  public UserResponseDto createUser(UserCreateRequestDto dto) {
    User user = userRepository.save(UserCreateMapper.INSTANCE.toEntity(dto));

    return UserResponseMapper.INSTANCE.toDto(user);
  }

  @Override
  public UserResponseDto updateUser(String id, UserUpdateRequestDto dto) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new NoSuchElementException("Failed to find user by id {" + id + "}");
    }

    user.update(dto);
    user = userRepository.save(user);

    return UserResponseMapper.INSTANCE.toDto(user);
  }

  @Override
  public void deleteUser(String id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new NoSuchElementException("Failed to find user by id {" + id + "}");
    }

    userRepository.delete(user);
  }

  // user_role
  @Override
  public List<UserRoleResponseDto> getUserRoles() {
    List<UserRole> userRoleList = userRoleRepository.findAll();
    if (userRoleList == null) {
      return new ArrayList<>();
    }

    return UserRoleResponseMapper.INSTANCE.toDtoList(userRoleList);
  }

  @Override
  public UserRoleResponseDto getUserRoleById(String id) {
    UserRole userRole = userRoleRepository.findById(id).orElse(null);
    if (userRole == null) {
      throw new NoSuchElementException("Failed to find user_role by id {" + id + "}");
    }

    return UserRoleResponseMapper.INSTANCE.toDto(userRole);
  }

  @Override
  public UserRoleResponseDto getUserRoleByUser(String userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      throw new NoSuchElementException("Failed to find user by id {" + userId + "}");
    }

    UserRole userRole = userRoleRepository.findByUser(user).orElse(null);
    if (userRole == null) {
      throw new NoSuchElementException("Failed to find user_role by user {" + user.getId() + "}");
    }

    return UserRoleResponseMapper.INSTANCE.toDto(userRole);
  }

  @Override
  public UserRoleResponseDto createUserRole(UserRoleCreateRequestDto dto) {
    User user = userRepository.findById(dto.getUserId()).orElse(null);
    if (user == null) {
      throw new NoSuchElementException("Failed to find user by {" + dto.getUserId() + "}");
    }
    Role role = roleRepository.findById(dto.getRoleId()).orElse(null);
    if (role == null) {
      throw new NoSuchElementException("Failed to find role by {" + dto.getRoleId() + "}");
    }

    UserRole userRole = userRoleRepository.findByUserAndRole(user, role).orElse(null);
    if (userRole == null) {
      throw new NoSuchElementException("Failed to find user_role");
    }

    return UserRoleResponseMapper.INSTANCE.toDto(userRole);
  }

  @Override
  public UserRoleResponseDto updateUserRole(String id, UserRoleUpdateRequestDto dto) {
    UserRole userRole = userRoleRepository.findById(id).orElse(null);
    if (userRole == null) {
      throw new NoSuchElementException("Failed to find user_role by id {" + id + "}");
    }

    userRole.update(dto);
    userRoleRepository.save(userRole);

    return UserRoleResponseMapper.INSTANCE.toDto(userRole);
  }

  @Override
  public void deleteUserRole(String id) {
    if (!userRoleRepository.existsById(id)) {
      throw new NoSuchElementException("Failed to find user_role by id {" + id + "}");
    }

    userRoleRepository.deleteById(id);
  }
}

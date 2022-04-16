package com.example.usecasewithmapstruct.domain.user.service;

import com.example.usecasewithmapstruct.domain.user.dto.user.UserCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleUpdateRequestDto;
import java.util.List;

public interface UserService {

  // user
  List<UserResponseDto> getUsers();

  UserResponseDto getUserById(String id);

  UserResponseDto createUser(UserCreateRequestDto dto);

  UserResponseDto updateUser(String id, UserUpdateRequestDto dto);

  void deleteUser(String id);


  // user_role
  List<UserRoleResponseDto> getUserRoles();

  UserRoleResponseDto getUserRoleById(String id);

  UserRoleResponseDto getUserRoleByUser(String userId);

  UserRoleResponseDto createUserRole(UserRoleCreateRequestDto dto);

  UserRoleResponseDto updateUserRole(String id, UserRoleUpdateRequestDto dto);

  void deleteUserRole(String id);
}

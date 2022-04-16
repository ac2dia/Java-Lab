package com.example.usecasewithmapstruct.domain.role.service;

import com.example.usecasewithmapstruct.domain.role.dto.RoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleResponseDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleUpdateRequestDto;
import java.util.List;

public interface RoleService {

  List<RoleResponseDto> getRoles();

  RoleResponseDto getRoleById(String id);

  RoleResponseDto createRole(RoleCreateRequestDto dto);

  RoleResponseDto updateRole(String id, RoleUpdateRequestDto dto);

  void deleteRole(String id);
}

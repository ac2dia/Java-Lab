package com.example.usecasewithmapstruct.domain.role.service;

import com.example.usecasewithmapstruct.domain.role.dto.RoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleResponseDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.domain.role.mapper.RoleCreateMapper;
import com.example.usecasewithmapstruct.domain.role.mapper.RoleResponseMapper;
import com.example.usecasewithmapstruct.domain.role.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<RoleResponseDto> getRoles() {
    List<Role> roleList = roleRepository.findAll();
    if (roleList == null) {
      return new ArrayList<>();
    }

    return RoleResponseMapper.INSTANCE.toDtoList(roleList);
  }

  @Override
  public RoleResponseDto getRoleById(String id) {
    Role role = roleRepository.findById(id).orElse(null);
    if (role == null) {
      throw new NoSuchElementException("Failed to find role by id {" + id + "}");
    }

    return RoleResponseMapper.INSTANCE.toDto(role);
  }

  @Override
  public RoleResponseDto createRole(RoleCreateRequestDto dto) {
    Role role = roleRepository.save(RoleCreateMapper.INSTANCE.toEntity(dto));

    return RoleResponseMapper.INSTANCE.toDto(role);
  }

  @Override
  public RoleResponseDto updateRole(String id, RoleUpdateRequestDto dto) {
    Role role = roleRepository.findById(id).orElse(null);
    if (role == null) {
      throw new NoSuchElementException("Failed to find role by id {" + id + "}");
    }

    role.update(dto);
    role = roleRepository.save(role);

    return RoleResponseMapper.INSTANCE.toDto(role);
  }

  @Override
  public void deleteRole(String id) {
    Role role = roleRepository.findById(id).orElse(null);
    if (role == null) {
      throw new NoSuchElementException("Failed to find role by id {" + id + "}");
    }

    roleRepository.delete(role);
  }
}

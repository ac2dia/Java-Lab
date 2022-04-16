package com.example.usecasewithmapstruct.domain.user.mapper.userrole;

import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleResponseDto;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleResponseMapper extends GenericMapper<UserRoleResponseDto, UserRole> {

  UserRoleResponseMapper INSTANCE = Mappers.getMapper(UserRoleResponseMapper.class);

  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "roleId", source = "role.id")
  @Override
  UserRoleResponseDto toDto(UserRole userRole);
}

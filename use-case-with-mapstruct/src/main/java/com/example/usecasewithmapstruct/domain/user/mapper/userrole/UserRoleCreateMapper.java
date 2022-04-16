package com.example.usecasewithmapstruct.domain.user.mapper.userrole;

import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleCreateMapper extends GenericMapper<UserRoleCreateRequestDto, UserRole> {

  UserRoleCreateMapper INSTANCE = Mappers.getMapper(UserRoleCreateMapper.class);

  @Mapping(target = "user.id", source = "userId")
  @Mapping(target = "role.id", source = "roleId")
  @Override
  UserRole toEntity(UserRoleCreateRequestDto userRoleCreateRequestDto);
}

package com.example.usecasewithmapstruct.domain.user.mapper.userrole;

import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleUpdateMapper extends GenericMapper<UserRoleUpdateRequestDto, UserRole> {

  UserRoleUpdateMapper INSTANCE = Mappers.getMapper(UserRoleUpdateMapper.class);
}

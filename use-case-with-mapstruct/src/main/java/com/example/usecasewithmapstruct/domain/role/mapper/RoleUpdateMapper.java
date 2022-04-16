package com.example.usecasewithmapstruct.domain.role.mapper;

import com.example.usecasewithmapstruct.domain.role.dto.RoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleUpdateMapper extends GenericMapper<RoleUpdateRequestDto, Role> {

  RoleUpdateMapper INSTANCE = Mappers.getMapper(RoleUpdateMapper.class);
}

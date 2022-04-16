package com.example.usecasewithmapstruct.domain.role.mapper;

import com.example.usecasewithmapstruct.domain.role.dto.RoleResponseDto;
import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleResponseMapper extends GenericMapper<RoleResponseDto, Role> {

  RoleResponseMapper INSTANCE = Mappers.getMapper(RoleResponseMapper.class);
}

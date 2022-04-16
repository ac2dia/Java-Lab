package com.example.usecasewithmapstruct.domain.role.mapper;

import com.example.usecasewithmapstruct.domain.role.dto.RoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleCreateMapper extends GenericMapper<RoleCreateRequestDto, Role> {

  RoleCreateMapper INSTANCE = Mappers.getMapper(RoleCreateMapper.class);
}

package com.example.usecasewithmapstruct.domain.user.mapper.user;

import com.example.usecasewithmapstruct.domain.user.dto.user.UserCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.entity.User;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCreateMapper extends GenericMapper<UserCreateRequestDto, User> {

  UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

  // @TODO toEntity 과정 중 password 컬럼에 대해 encode 필요
  @Override
  User toEntity(UserCreateRequestDto userCreateRequestDto);
}

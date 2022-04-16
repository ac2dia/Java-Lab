package com.example.usecasewithmapstruct.domain.user.mapper.user;

import com.example.usecasewithmapstruct.domain.user.dto.user.UserUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.entity.User;
import com.example.usecasewithmapstruct.global.model.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserUpdateMapper extends GenericMapper<UserUpdateRequestDto, User> {

  UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);
}

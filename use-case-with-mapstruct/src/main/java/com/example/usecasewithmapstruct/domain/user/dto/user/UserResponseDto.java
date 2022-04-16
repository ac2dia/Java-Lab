package com.example.usecasewithmapstruct.domain.user.dto.user;

import com.example.usecasewithmapstruct.global.model.dto.BaseAuditingDto;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto extends BaseAuditingDto {

  @NotNull
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String email;
}

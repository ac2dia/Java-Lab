package com.example.usecasewithmapstruct.domain.user.dto.user;

import com.sun.istack.NotNull;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto implements Serializable {

  @NotNull
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String email;
}

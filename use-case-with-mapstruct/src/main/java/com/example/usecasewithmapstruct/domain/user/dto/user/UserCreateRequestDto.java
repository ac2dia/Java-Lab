package com.example.usecasewithmapstruct.domain.user.dto.user;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
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

  @Max(20)
  @NotNull
  private String username;

  @NotNull
  private String password;

  @Max(20)
  @NotNull
  private String name;

  @Max(40)
  @NotNull
  @Email
  private String email;
}

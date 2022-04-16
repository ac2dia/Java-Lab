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
public class UserUpdateRequestDto implements Serializable {

  @NotNull
  private String name;

  @NotNull
  private String email;
}

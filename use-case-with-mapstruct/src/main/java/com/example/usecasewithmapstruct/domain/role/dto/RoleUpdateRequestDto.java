package com.example.usecasewithmapstruct.domain.role.dto;

import com.example.usecasewithmapstruct.global.Constants.ROLE_TYPE;
import com.sun.istack.NotNull;
import java.io.Serializable;
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
public class RoleUpdateRequestDto implements Serializable {

  @Max(20)
  @NotNull
  private ROLE_TYPE type;

  @Max(20)
  @NotNull
  private String name;

  private String description;
}

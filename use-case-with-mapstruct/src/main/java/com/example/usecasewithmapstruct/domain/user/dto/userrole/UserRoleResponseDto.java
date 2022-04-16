package com.example.usecasewithmapstruct.domain.user.dto.userrole;

import java.io.Serializable;
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
public class UserRoleResponseDto implements Serializable {

  private String id;

  private String userId;

  private String roleId;

  private String description;
}

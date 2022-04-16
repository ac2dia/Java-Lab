package com.example.usecasewithmapstruct.domain.user.dto.userrole;

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
public class UserRoleCreateRequestDto implements Serializable {

  private String userId;

  private String roleId;

  private String description;
}

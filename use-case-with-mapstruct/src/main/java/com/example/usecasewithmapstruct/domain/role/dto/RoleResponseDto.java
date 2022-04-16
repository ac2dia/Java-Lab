package com.example.usecasewithmapstruct.domain.role.dto;

import com.example.usecasewithmapstruct.global.Constants.ROLE_TYPE;
import com.example.usecasewithmapstruct.global.model.dto.BaseAuditingDto;
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
public class RoleResponseDto extends BaseAuditingDto {

  private ROLE_TYPE type;

  private String name;

  private String description;
}

package com.example.usecasewithmapstruct.domain.role.entity;

import com.example.usecasewithmapstruct.domain.role.dto.RoleUpdateRequestDto;
import com.example.usecasewithmapstruct.global.Constants.ROLE_TYPE;
import com.example.usecasewithmapstruct.global.model.entity.BaseAuditingEntity;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
@Entity(name = "role")
public class Role extends BaseAuditingEntity {

  @NotNull
  @Enumerated
  @Column(unique = true, length = 20)
  private ROLE_TYPE type;

  @NotNull
  @Column(unique = true, nullable = false, length = 20)
  private String name;

  @Column(length = 2048)
  private String description;

  public void update(RoleUpdateRequestDto dto) {
    this.type = dto.getType();
    this.name = dto.getName();
    this.description = dto.getDescription();
  }
}

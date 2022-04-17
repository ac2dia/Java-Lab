package com.example.usecasewithmapstruct.domain.user.entity;

import com.example.usecasewithmapstruct.domain.user.dto.user.UserUpdateRequestDto;
import com.example.usecasewithmapstruct.global.model.entity.BaseAuditingEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "user")
public class User extends BaseAuditingEntity {

  @Column(unique = true, length = 20)
  private String username;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(unique = true, nullable = false, length = 40)
  private String email;

  public void update(UserUpdateRequestDto dto) {
    this.name = dto.getName();
    this.email = dto.getEmail();
  }

  public void updatePassword(String password) {
    // @TODO password encode 필요
    this.password = password;
  }
}

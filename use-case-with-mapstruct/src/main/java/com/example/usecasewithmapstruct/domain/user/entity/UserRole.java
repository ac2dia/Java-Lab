package com.example.usecasewithmapstruct.domain.user.entity;

import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleUpdateRequestDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user_role")
public class UserRole implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", columnDefinition = "VARCHAR(36)", insertable = false, updatable = false, nullable = false)
  private String id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @Column(length = 2048)
  private String description;

  public void update(UserRoleUpdateRequestDto dto) {
    this.description = dto.getDescription();
  }
}

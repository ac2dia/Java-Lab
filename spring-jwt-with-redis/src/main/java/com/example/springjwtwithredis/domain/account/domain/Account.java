package com.example.springjwtwithredis.domain.account.domain;

import com.example.springjwtwithredis.domain.model.BaseTimeEntity;
import com.example.springjwtwithredis.global.Constants.ROLE;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
public class Account extends BaseTimeEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", columnDefinition = "VARCHAR(36)", insertable = false, updatable = false, nullable = false)
  private String id;

  @Column(nullable = false, length = 30)
  private String username;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ROLE role;

  public Account() {
  }

  @Builder
  public Account(LocalDateTime createdDate, LocalDateTime modifiedDate,
      String username, String email, String password, ROLE role) {
    super(createdDate, modifiedDate);
    this.username = username;
    this.email = email;
    this.password = password; // @TODO password encoder 적용 필요
    this.role = role;
  }

  public Account update(String username, String password) {
    this.username = username;
    this.password = password; // @TODO password encoder 적용 필요

    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }

  @Override
  public String toString() {
    return "Account{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
//        ", password='" + password + '\'' + // 패스워드는 비공개
        ", role=" + role +
        ", createdDate=" + createdDate +
        ", modifiedDate=" + modifiedDate +
        '}';
  }
}

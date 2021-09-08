package com.example.mapperproject.domain;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "brands")
public class Brands extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final long id = 0;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private int est;

  protected Brands() {
  }

  @Builder
  protected Brands(String name, String email, int est,
      String createdDate, String modifiedDate) {
    super(createdDate, modifiedDate);

    this.name = name;
    this.email = email;
    this.est = est;
  }
}

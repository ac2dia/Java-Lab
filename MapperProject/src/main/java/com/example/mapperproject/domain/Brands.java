package com.example.mapperproject.domain;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Brands {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final long id = 0;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private int est;
}

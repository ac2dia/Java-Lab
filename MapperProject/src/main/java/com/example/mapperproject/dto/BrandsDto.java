package com.example.mapperproject.dto;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandsDto implements Serializable {

  private String name;
  private String email;
  private int est; // establish
}

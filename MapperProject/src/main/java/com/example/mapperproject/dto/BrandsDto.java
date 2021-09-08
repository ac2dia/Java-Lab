package com.example.mapperproject.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandsDto extends BaseTimeDto {

  private String name;
  private String email;
  private int est; // establish

  protected BrandsDto() {
  }

  @Builder
  protected BrandsDto(String name, String email, int est,
      String createdDate, String modifiedDate) {
    super(createdDate, modifiedDate);

    this.name = name;
    this.email = email;
    this.est = est;
  }
}

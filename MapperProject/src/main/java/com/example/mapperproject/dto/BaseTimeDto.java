package com.example.mapperproject.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class BaseTimeDto implements Serializable {

  private String createdDate;
  private String modifiedDate;

  protected BaseTimeDto() {
  }

  protected BaseTimeDto(String createdDate, String modifiedDate) {
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }
}

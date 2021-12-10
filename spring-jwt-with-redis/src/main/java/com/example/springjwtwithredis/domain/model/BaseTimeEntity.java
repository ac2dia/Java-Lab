package com.example.springjwtwithredis.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity implements Serializable {

  @CreatedDate
  protected LocalDateTime createdDate;

  @LastModifiedDate
  protected LocalDateTime modifiedDate;

  protected BaseTimeEntity() {
  }

  protected BaseTimeEntity(LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }
}
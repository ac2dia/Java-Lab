package com.example.springoauth2client.domain.model;

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
public abstract class BaseTimeEntity {

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
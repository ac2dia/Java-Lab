package com.example.mapperproject.dto;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseTimeDto<LocalDateTime> {

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;
}

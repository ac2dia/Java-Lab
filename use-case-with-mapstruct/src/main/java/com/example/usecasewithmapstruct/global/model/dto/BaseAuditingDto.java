package com.example.usecasewithmapstruct.global.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseAuditingDto implements Serializable {

  protected String id;

  protected Timestamp createdAt;

  protected Timestamp updatedAt;
}

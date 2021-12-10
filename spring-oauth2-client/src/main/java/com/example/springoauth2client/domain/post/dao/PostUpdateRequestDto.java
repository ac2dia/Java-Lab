package com.example.springoauth2client.domain.post.dao;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto implements Serializable {

  private String title;
  private String content;

  @Builder
  public PostUpdateRequestDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}

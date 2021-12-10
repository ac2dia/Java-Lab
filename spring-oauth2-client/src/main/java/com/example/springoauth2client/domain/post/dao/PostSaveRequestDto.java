package com.example.springoauth2client.domain.post.dao;

import com.example.springoauth2client.domain.post.domain.Post;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto implements Serializable {

  private String title;
  private String content;
  private String author;

  @Builder
  public PostSaveRequestDto(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Post toEntity() {
    return Post.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
  }
}

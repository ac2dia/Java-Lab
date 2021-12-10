package com.example.springoauth2client.domain.post.dao;

import com.example.springoauth2client.domain.post.domain.Post;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostListResponseDto implements Serializable {

  private String id;
  private String title;
  private String author;
  private LocalDateTime modifiedDate;

  public PostListResponseDto(Post entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }
}

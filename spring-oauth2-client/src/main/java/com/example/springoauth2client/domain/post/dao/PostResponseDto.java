package com.example.springoauth2client.domain.post.dao;

import com.example.springoauth2client.domain.post.domain.Post;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class PostResponseDto implements Serializable {

  private String id;
  private String title;
  private String content;
  private String author;

  public PostResponseDto(Post entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }
}

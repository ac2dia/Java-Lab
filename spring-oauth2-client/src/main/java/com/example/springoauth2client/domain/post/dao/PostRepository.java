package com.example.springoauth2client.domain.post.dao;

import com.example.springoauth2client.domain.post.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, String> {

  @Query("SELECT p From Post p ORDER BY p.id DESC")
  List<Post> findAllDesc();
}

package com.example.springoauth2client.domain.post.api;


import com.example.springoauth2client.domain.post.application.PostService;
import com.example.springoauth2client.domain.post.dao.PostResponseDto;
import com.example.springoauth2client.domain.post.dao.PostSaveRequestDto;
import com.example.springoauth2client.domain.post.dao.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

  private final PostService postService;

  @PostMapping
  public String save(@RequestBody PostSaveRequestDto requestDto) {
    return postService.save(requestDto);
  }

  @PutMapping("/{id}")
  public String update(@PathVariable String id, @RequestBody PostUpdateRequestDto requestDto) {
    return postService.update(id, requestDto);
  }

  @GetMapping("/{id}")
  public PostResponseDto findById(@PathVariable String id) {
    return postService.findById(id);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable String id) {
    postService.delete(id);
    return id;
  }
}
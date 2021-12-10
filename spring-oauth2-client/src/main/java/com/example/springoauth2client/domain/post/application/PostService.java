package com.example.springoauth2client.domain.post.application;

import com.example.springoauth2client.domain.post.dao.PostListResponseDto;
import com.example.springoauth2client.domain.post.dao.PostRepository;
import com.example.springoauth2client.domain.post.dao.PostResponseDto;
import com.example.springoauth2client.domain.post.dao.PostSaveRequestDto;
import com.example.springoauth2client.domain.post.dao.PostUpdateRequestDto;
import com.example.springoauth2client.domain.post.domain.Post;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public String save(PostSaveRequestDto requestDto) {
    return postRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public String update(String id, PostUpdateRequestDto requestDto) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    post.update(requestDto.getTitle(), requestDto.getContent());
    return id;
  }

  public PostResponseDto findById(String id) {
    Post entity = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    return new PostResponseDto(entity);
  }

  @Transactional
  public List<PostListResponseDto> findAllDesc() {
    return postRepository.findAllDesc().stream()
        .map(PostListResponseDto::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public void delete(String id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

    postRepository.delete(post);
  }
}
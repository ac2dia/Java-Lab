package com.example.springoauth2client.domain.post.api;

import com.example.springoauth2client.domain.post.application.PostService;
import com.example.springoauth2client.domain.post.dao.PostResponseDto;
import com.example.springoauth2client.domain.user.dao.SessionUser;
import com.example.springoauth2client.global.annotation.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final PostService postService;

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postService.findAllDesc());

    if (user != null) {
      model.addAttribute("userName", user.getName());
    }

    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable String id, Model model) {

    PostResponseDto dto = postService.findById(id);
    model.addAttribute("post", dto);

    return "posts-update";
  }
}

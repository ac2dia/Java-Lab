package com.example.jwt.controller;

import com.example.jwt.model.Todo;
import com.example.jwt.provider.JwtTokenProvider;
import com.example.jwt.repository.TodoRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class TodoController {

  private final TodoRepository todoRepository;
  private final JwtTokenProvider jwtTokenProvider;

  // 작성
  @PostMapping("/todo")
  public ResponseEntity<String> write(@RequestHeader String Authorization, @RequestBody Todo todo) {
    if (!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    return ResponseEntity.status(HttpStatus.OK).body(todoRepository.save(Todo.builder()
        .email(todo.getEmail())
        .title(todo.getTitle())
        .content(todo.getContent())
        .build()).getId());
  }

  // 조회
  @GetMapping("/todo/{email}")
  public ResponseEntity<List<Todo>> read(@RequestHeader String Authorization,
      @PathVariable String email) {
    if (!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }

    return ResponseEntity.status(HttpStatus.OK).body(todoRepository.findAllByEmail(email));
  }
}

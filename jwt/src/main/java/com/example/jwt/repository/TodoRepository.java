package com.example.jwt.repository;

import com.example.jwt.model.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, String> {

  List<Todo> findAllByEmail(String email);

  Optional<Todo> findByEmail(String email);
}

package io.springfox.springfoxswagger2.repository;

import io.springfox.springfoxswagger2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

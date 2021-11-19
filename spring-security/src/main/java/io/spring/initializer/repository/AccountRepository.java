package io.spring.initializer.repository;

import io.spring.initializer.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  Account findByUsername(String username);
}
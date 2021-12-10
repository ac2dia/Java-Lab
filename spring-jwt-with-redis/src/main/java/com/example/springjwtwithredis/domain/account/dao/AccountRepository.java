package com.example.springjwtwithredis.domain.account.dao;

import com.example.springjwtwithredis.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  Account findByUsername(String username);

  Account findByEmail(String email);
}

package com.example.springjwtwithredis.domain.account.application;

import com.example.springjwtwithredis.domain.account.dao.AccountRepository;
import com.example.springjwtwithredis.domain.account.domain.Account;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

  private final AccountRepository repository;

  private final PasswordEncoder passwordEncoder;

  public void save(Account d) {
    if (null == d) {
      return;
    }

    repository.save(d);
  }

  public List<Account> findAll() {
    List<Account> accountList = repository.findAll();

    return accountList;
  }

  public Account findById(String id) {
    Account account = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

    return account;
  }

  public Account findByUsername(String username) {
    Account account = repository.findByUsername(username);

    return account;
  }

  public Account findByEmail(String email) {
    Account account = repository.findByEmail(email);

    return account;
  }


  public void deleteById(String id) {
    if (repository.findById(id).isEmpty()) {
      return;
    }

    repository.deleteById(id);
  }

  public String encode(String password) {
    return passwordEncoder.encode(password);
  }
}

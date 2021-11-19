package io.spring.initializer.controller;

import io.spring.initializer.dto.AccountDto;
import io.spring.initializer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/account")
  public AccountDto createAccount(@RequestBody AccountDto accountDto) {
    return accountService.createNew(accountDto);
  }
}

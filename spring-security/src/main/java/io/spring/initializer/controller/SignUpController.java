package io.spring.initializer.controller;

import io.spring.initializer.domain.Account;
import io.spring.initializer.dto.AccountDto;
import io.spring.initializer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

  private final AccountService accountService;

  @GetMapping
  public String signUpForm(Model model) {
    model.addAttribute("account", new Account());
    return "signUp";
  }

  @PostMapping
  public String processSignUp(@RequestBody AccountDto accountDto) {
    accountService.createNew(accountDto);
    return "redirect:/";
  }

}

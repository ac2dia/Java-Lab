package io.spring.initializer.dto;


import com.sun.tools.javac.util.List;
import io.spring.initializer.domain.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAccount extends User {

  private final Account account;

  public UserAccount(Account account) {
    super(account.getUsername(), account.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole())));
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

}
package com.example.springjwtwithredis.domain.account.application;

import com.example.springjwtwithredis.domain.account.dao.AccountRepository;
import com.example.springjwtwithredis.domain.account.domain.Account;
import com.example.springjwtwithredis.global.Constants.ROLE;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = repository.findByUsername(username);

    List<GrantedAuthority> roles = new ArrayList<>();

    if (account == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    if ((account.getRole().toString()).equals(ROLE.ADMIN.toString())) {
      roles.add(new SimpleGrantedAuthority(ROLE.ADMIN.toString()));
    } else {
      roles.add(new SimpleGrantedAuthority(ROLE.USER.toString()));
      roles.add(new SimpleGrantedAuthority(ROLE.GUSET.toString()));
    }
    return new User(account.getUsername(), account.getPassword(), roles);
  }

}

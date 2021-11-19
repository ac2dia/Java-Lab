package io.spring.initializer.service;

import io.spring.initializer.domain.Account;
import io.spring.initializer.dto.AccountDto;
import io.spring.initializer.dto.UserAccount;
import io.spring.initializer.mapper.AccountMapper;
import io.spring.initializer.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username);
    if (account == null) {
      throw new UsernameNotFoundException(username);
    }

    return new UserAccount(account);

//    return User.builder()
//        .username(account.getUsername())
//        .password(account.getPassword())
//        .roles(account.getRole())
//        .build();
  }

  public AccountDto createNew(AccountDto accountDto) {
    accountDto.encodePassword(PasswordEncoderFactories.createDelegatingPasswordEncoder());

    return AccountMapper.INSTANCE.toDto(
        accountRepository.save(AccountMapper.INSTANCE.toEntity(accountDto)));
  }
}

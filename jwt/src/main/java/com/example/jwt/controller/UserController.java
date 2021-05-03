package com.example.jwt.controller;

import com.example.jwt.model.User;
import com.example.jwt.provider.JwtTokenProvider;
import com.example.jwt.repository.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class UserController {

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  // 회원가입
  @PostMapping("/join")
  public String join(@RequestBody User user) {
    return userRepository.save(User.builder()
        .email(user.getEmail())
        .password(passwordEncoder.encode(user.getPassword()))
        .roles(Collections.singletonList("USER")) // 최초 가입시 USER 로 설정
        .build()).getId();
  }

  // 로그인
  @PostMapping("/login")
  public String login(@RequestBody User user) {
    User member = userRepository.findByEmail(user.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
    if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }
    return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
  }
}

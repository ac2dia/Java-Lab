package com.example.springjwtwithredis.domain.account.api;

import com.example.springjwtwithredis.domain.account.application.AccountService;
import com.example.springjwtwithredis.domain.account.application.JwtUserDetailsService;
import com.example.springjwtwithredis.domain.account.dao.Token;
import com.example.springjwtwithredis.domain.account.domain.Account;
import com.example.springjwtwithredis.global.Constants.ROLE;
import com.example.springjwtwithredis.global.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserApi {

  private final RedisTemplate<String, Object> redisTemplate;
  private final AccountService accountService;
  private final JwtUserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  @DeleteMapping("/users/{id}")
  public void deleteUser(String id) {
    accountService.deleteById(id);
  }

  @GetMapping(path = "/users")
  public Iterable<Account> getAllUsers() {
    return accountService.findAll();
  }

  @PostMapping(path = "/users/logout")
  public ResponseEntity<?> logout(@RequestHeader("X-Auth-Token") String accessToken) {
    String username = null;

    try {
      username = jwtTokenUtil.getUsernameFromToken(accessToken);
    } catch (IllegalArgumentException e) {
    } catch (ExpiredJwtException e) { //expire됐을 때
      username = e.getClaims().getSubject();
      log.info("username from expired access token: {}", username);
    }

    try {
      if (redisTemplate.opsForValue().get(username) != null) {
        //delete refresh token
        redisTemplate.delete(username);
      }
    } catch (IllegalArgumentException e) {
      log.warn("user does not exist");
    }

    //cache logout token for 10 minutes!
    log.info(" logout ing : {}", accessToken);
    redisTemplate.opsForValue().set(accessToken, true);
    redisTemplate.expire(accessToken, 10 * 6 * 1000, TimeUnit.MILLISECONDS);

    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping(path = "/users/check")
  public Map<String, Object> checker(@RequestHeader("X-Auth-Token") String accessToken) {
    String username = null;
    Map<String, Object> map = new HashMap<>();
    try {
      username = jwtTokenUtil.getUsernameFromToken(accessToken);
    } catch (IllegalArgumentException e) {
      log.warn("Unable to get JWT Token");
    } catch (ExpiredJwtException e) {
    }

    if (username != null) {
      map.put("success", true);
      map.put("username", username);
    } else {
      map.put("success", false);
    }
    return map;
  }

  @PostMapping(path = "/users/login")
  public Map<String, Object> login(@RequestBody Account account) throws Exception {
    final String username = account.getUsername();
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, account.getPassword()));
    } catch (Exception e) {
      throw e;
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
    final String refreshToken = jwtTokenUtil.generateRefreshToken(username);

    Token token = new Token();
    token.setUsername(username);
    token.setRefreshToken(refreshToken);

    //generate Token and save in redis
    ValueOperations<String, Object> vop = redisTemplate.opsForValue();
    vop.set(username, token);

    log.info("generated access token: " + accessToken);
    log.info("generated refresh token: " + refreshToken);
    Map<String, Object> map = new HashMap<>();
    map.put("accessToken", accessToken);
    map.put("refreshToken", refreshToken);
    return map;
  }


  @PostMapping(path = "/users")
  public Map<String, Object> addNewUser(@RequestBody Account account) {
    Map<String, Object> map = new HashMap<>();

    if (accountService.findByUsername(account.getUsername()) == null) {
      Account accountDto = Account.builder()
          .username(account.getUsername())
          .email(account.getEmail())
          .password(passwordEncoder.encode(account.getPassword()))
          .role(ROLE.ADMIN)
          .build();

      map.put("success", true);
      accountService.save(accountDto);
      return map;
    } else {
      map.put("success", false);
      map.put("message", "duplicated email");
    }
    return map;
  }


  @GetMapping(path = "/users/email")
  public boolean checkEmail(@RequestParam String email) {

    return accountService.findByEmail(email) == null;
  }

  @PostMapping(path = "/users/refresh")
  public Map<String, Object> requestForNewAccessToken(@RequestBody Token token) {
    String accessToken = null;
    String refreshToken = null;
    String refreshTokenFromDb = null;
    String username = null;
    Map<String, Object> map = new HashMap<>();
    try {
      accessToken = token.getAccessToken();
      refreshToken = token.getRefreshToken();
      log.info("access token in rnat: {}", accessToken);
      try {
        username = jwtTokenUtil.getUsernameFromToken(accessToken);
      } catch (IllegalArgumentException e) {

      } catch (ExpiredJwtException e) { //expire됐을 때
        username = e.getClaims().getSubject();
        log.info("username from expired access token: {}", username);
      }

      if (refreshToken != null) { //refresh를 같이 보냈으면.
        try {
          ValueOperations<String, Object> vop = redisTemplate.opsForValue();
          Token result = (Token) vop.get(username);
          refreshTokenFromDb = result.getRefreshToken();
          log.info("rt from db: {}", refreshTokenFromDb);
        } catch (IllegalArgumentException e) {
          log.warn("illegal argument!!");
        }

        //둘이 일치하고 만료도 안됐으면 재발급 해주기.
        if (refreshToken.equals(refreshTokenFromDb) && !jwtTokenUtil.isTokenExpired(refreshToken)) {
          final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          String newtok = jwtTokenUtil.generateAccessToken(userDetails);
          map.put("success", true);
          map.put("accessToken", newtok);
        } else {
          map.put("success", false);
          map.put("msg", "refresh token is expired.");
        }
      } else { //refresh token이 없으면
        map.put("success", false);
        map.put("msg", "your refresh token does not exist.");
      }

    } catch (Exception e) {
      throw e;
    }

    return map;
  }

}
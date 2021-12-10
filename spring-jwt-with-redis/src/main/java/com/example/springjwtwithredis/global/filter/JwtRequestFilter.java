package com.example.springjwtwithredis.global.filter;

import com.example.springjwtwithredis.global.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

  private final RedisTemplate<String, Object> redisTemplate;
  private final JwtTokenUtil jwtTokenUtil;

  public Authentication getAuthentication(String token) {
    Map<String, Object> parseInfo = jwtTokenUtil.getUserParseInfo(token);
    log.info("parseInfo: {}", parseInfo);

    List<String> roles = (List) parseInfo.get("role");

    Collection<GrantedAuthority> tmp = new ArrayList<>();
    for (String role : roles) {
      tmp.add(new SimpleGrantedAuthority(role));
    }
    UserDetails userDetails = User.builder().username(String.valueOf(parseInfo.get("username")))
        .authorities(tmp).password("asd").build();
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

  @Bean
  public FilterRegistrationBean JwtRequestFilterRegistration(JwtRequestFilter filter) {
    FilterRegistrationBean registration = new FilterRegistrationBean(filter);
    registration.setEnabled(false);
    return registration;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    log.info("REQUEST : {}", request.getHeader("X-Auth-Token"));

    String requestTokenHeader = request.getHeader("X-Auth-Token");
    log.info("Token Header: {}", requestTokenHeader);

    String username = null;
    String jwtToken = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      log.info("Token in request filter: {}", jwtToken);

      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        log.warn("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
      }
    } else {
      log.warn("JWT Token does not begin with Bearer String");
    }

    if (username == null) {
      log.info("token maybe expired: username is null.");
    } else if (redisTemplate.opsForValue().get(jwtToken) != null) {
      log.warn("this token already logout!");
    } else {
      //DB access 대신에 파싱한 정보로 유저 만들기!
      Authentication authen = getAuthentication(jwtToken);
      //만든 authentication 객체로 매번 인증받기
      SecurityContextHolder.getContext().setAuthentication(authen);
      response.setHeader("username", username);
    }
    chain.doFilter(request, response);
  }
}


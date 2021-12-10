package com.example.springjwtwithredis.global.config;

import com.example.springjwtwithredis.global.aop.JwtAuthenticationEntryPoint;
import com.example.springjwtwithredis.global.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtRequestFilter jwtRequestFilter;
  private final UserDetailsService jwtUserDetailsService;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        httpBasic().disable()
        .cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests().antMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
        .and()
        .authorizeRequests().anyRequest().permitAll()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

//  @Bean
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    // configure AuthenticationManager so that it knows from where to load
//    // user for matching credentials
//    // Use BCryptPasswordEncoder
//    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}

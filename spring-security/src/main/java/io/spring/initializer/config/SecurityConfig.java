package io.spring.initializer.config;

import io.spring.initializer.filter.LoggingFilter;
import io.spring.initializer.service.AccountService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AccountService accountService;

  public AccessDecisionManager accessDecisionManager() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
    webExpressionVoter.setExpressionHandler(handler);

    List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);
    return new AffirmativeBased(voters);
  }

  public SecurityExpressionHandler expressionHandler() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    return handler;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);

    http.authorizeRequests()
        .mvcMatchers("/", "/info", "/account/**", "/dashboard").permitAll()
        .mvcMatchers("/user").hasRole("USER")
        .mvcMatchers("/h2-console/**", "/admin").hasRole("ADMIN")
        .anyRequest().authenticated()
        .expressionHandler(expressionHandler());

    http.formLogin()
        .loginPage("/login")
        .permitAll();

    http.httpBasic();

    // Logout Filter
    http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true);

    // Token Based Authentication Filter
    http.rememberMe()
//                .rememberMeParameter("remember")
//                .alwaysRemember(true)
        .userDetailsService(accountService)
        .key("remember-me-sample");

    http.exceptionHandling()
//                .accessDeniedPage("/access-denied");
        .accessDeniedHandler(new AccessDeniedHandler() {
          @Override
          public void handle(HttpServletRequest request, HttpServletResponse response,
              AccessDeniedException accessDeniedException) throws IOException, ServletException {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
            String username = principal.getUsername();
            SecurityLogger.log(username + " is denied to access " + request.getRequestURI());
            response.sendRedirect("/access-denied");
          }
        });

    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("ac2dia").password("{noop}security").roles("USER").and()
        .withUser("admin").password("{noop}security").roles("ADMIN");
  }
}

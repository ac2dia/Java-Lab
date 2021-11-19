package io.spring.initializer.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.spring.initializer.dto.AccountDto;
import io.spring.initializer.service.AccountService;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountService accountService;

  @Test
  public void createUser() {
    AccountDto accountDto = AccountDto.builder()
        .username("ac2dia")
        .password("security")
        .role("USER")
        .build();
    accountService.createNew(accountDto);
  }

  @Test
  @Transactional  // 테스트 코드 실행 후 DB가 롤백됨
  public void login_success() throws Exception {
    String username = "ac2dia";
    String password = "security";
    createUser();
    mockMvc.perform(formLogin().user(username).password(password))
        .andExpect(authenticated());  // 응답 가능
  }

  @Test
  @Transactional
  public void login_fail() throws Exception {
    String username = "ac2dia";
    String password = "security";
    createUser();
    mockMvc.perform(formLogin().user(username).password("321"))
        .andExpect(unauthenticated());  // 응답 불가
  }

  @Test
  @WithAnonymousUser
  public void index_anonymous() throws Exception {
    mockMvc.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "ac2dia", password = "security", roles = "USER")
  public void index_user() throws Exception {
    mockMvc.perform(get("/"))   // 유저가 로그인을 한 상태라고 가정을 함 (DB에 있음을 의미하지 않음)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "ac2dia", password = "security", roles = "USER")
  public void admin_user() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "admin", password = "security", roles = "ADMIN")
  public void admin_admin() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isOk());
  }

}
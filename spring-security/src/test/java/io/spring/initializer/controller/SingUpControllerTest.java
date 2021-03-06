package io.spring.initializer.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SingUpControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void signUpForm() throws Exception {
    mockMvc.perform(get("/signUp"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("_csrf")));
  }

  @Test
  public void processSignUp() throws Exception {
    mockMvc.perform(post("/signUp")
            .param("username", "seungho")
            .param("password", "123")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }

}

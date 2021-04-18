package org.springframework.validation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.model.UserDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void savePostSuccess() throws Exception {

        UserDto userDto = UserDto.builder()
                .name("shlee")
                .phone("010-1234-5678")
                .email("ac2dia@gmail.com")
                .build();

        String userDtoJsonString = objectMapper.writeValueAsString(userDto);
        System.out.println(userDtoJsonString);

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJsonString))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void savePostFailed() throws Exception {

        UserDto userDto = UserDto.builder()
                .name("shlee")
                .build();

        String userDtoJsonString = objectMapper.writeValueAsString(userDto);
        System.out.println(userDtoJsonString);

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJsonString))
                .andExpect(status().is4xxClientError());

    }

}
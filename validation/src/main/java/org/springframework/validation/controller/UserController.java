package org.springframework.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.model.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/v1/users")
    public ResponseEntity<String> savePost(final @Valid @RequestBody UserDto userDto) {
        logger.info("UserDto = '{}'", userDto.toString());
        return ResponseEntity.ok().body("postDto 객체 검증 성공");
    }
}

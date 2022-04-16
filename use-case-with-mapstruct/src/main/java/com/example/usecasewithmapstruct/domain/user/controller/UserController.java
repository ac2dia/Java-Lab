package com.example.usecasewithmapstruct.domain.user.controller;

import com.example.usecasewithmapstruct.domain.user.dto.user.UserCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.user.UserUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleResponseDto;
import com.example.usecasewithmapstruct.domain.user.dto.userrole.UserRoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.user.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getUsers() {
    List<UserResponseDto> userResponseDtoList = userService.getUsers();

    return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
    UserResponseDto userResponseDto = userService.getUserById(id);

    return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> createUser(
      @RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
    UserResponseDto userResponseDto = userService.createUser(userCreateRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDto> updateUser(
      @PathVariable String id,
      @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
    UserResponseDto userResponseDto = userService.updateUser(id, userUpdateRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  // user_role
  @GetMapping("/roles")
  public ResponseEntity<List<UserRoleResponseDto>> getUserRoles() {
    List<UserRoleResponseDto> userRoleResponseDtoList = userService.getUserRoles();

    return ResponseEntity.status(HttpStatus.OK).body(userRoleResponseDtoList);
  }

  @GetMapping("/roles/{id}")
  public ResponseEntity<UserRoleResponseDto> getUserRoleById(@PathVariable String id) {
    UserRoleResponseDto userRoleResponseDto = userService.getUserRoleById(id);

    return ResponseEntity.status(HttpStatus.OK).body(userRoleResponseDto);
  }

  @GetMapping("/roles")
  public ResponseEntity<UserRoleResponseDto> getUserRoleByUser(@RequestParam String userId) {
    UserRoleResponseDto userRoleResponseDto = userService.getUserRoleByUser(userId);

    return ResponseEntity.status(HttpStatus.OK).body(userRoleResponseDto);
  }

  @PostMapping("/roles")
  public ResponseEntity<UserRoleResponseDto> createUserRole(
      @RequestBody @Valid UserRoleCreateRequestDto userRoleCreateRequestDto) {
    UserRoleResponseDto userRoleResponseDto = userService.createUserRole(userRoleCreateRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(userRoleResponseDto);
  }

  @PutMapping("/roles/{id}")
  public ResponseEntity<UserRoleResponseDto> updateUserRole(
      @PathVariable String id,
      @RequestBody @Valid UserRoleUpdateRequestDto userRoleUpdateRequestDto) {
    UserRoleResponseDto userRoleResponseDto = userService.updateUserRole(id,
        userRoleUpdateRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(userRoleResponseDto);
  }

  @DeleteMapping("/roles/{id}")
  public ResponseEntity<Void> deleteUserRole(@PathVariable String id) {
    userService.deleteUserRole(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

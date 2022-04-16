package com.example.usecasewithmapstruct.domain.role.controller;

import com.example.usecasewithmapstruct.domain.role.dto.RoleCreateRequestDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleResponseDto;
import com.example.usecasewithmapstruct.domain.role.dto.RoleUpdateRequestDto;
import com.example.usecasewithmapstruct.domain.role.service.RoleService;
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
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/roles")
@RequiredArgsConstructor
@RestController
public class RoleController {

  private final RoleService roleService;

  @GetMapping
  public ResponseEntity<List<RoleResponseDto>> getRoles() {
    List<RoleResponseDto> roleResponseDtoList = roleService.getRoles();

    return ResponseEntity.status(HttpStatus.OK).body(roleResponseDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable String id) {
    RoleResponseDto roleResponseDto = roleService.getRoleById(id);

    return ResponseEntity.status(HttpStatus.OK).body(roleResponseDto);
  }

  @PostMapping
  public ResponseEntity<RoleResponseDto> createRole(
      @RequestBody @Valid RoleCreateRequestDto roleCreateRequestDto) {
    RoleResponseDto roleResponseDto = roleService.createRole(roleCreateRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(roleResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RoleResponseDto> updateRole(
      @PathVariable String id,
      @RequestBody @Valid RoleUpdateRequestDto roleUpdateRequestDto) {
    RoleResponseDto roleResponseDto = roleService.updateRole(id, roleUpdateRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(roleResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRole(@PathVariable String id) {
    roleService.deleteRole(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

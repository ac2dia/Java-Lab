package com.example.mapperproject.controller;

import com.example.mapperproject.dto.BrandsDto;
import com.example.mapperproject.service.BrandsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class BrandsController {

  private final BrandsService service;

  @PostMapping(value = "/brands")
  public void create(@RequestBody BrandsDto d) {
    service.create(d);
  }

  @GetMapping(value = "/brands")
  public List<BrandsDto> selectAll() {
    return service.selectAll();
  }

  @GetMapping(value = "/brands/{id}")
  public BrandsDto select(@PathVariable Long id) {
    return service.select(id);
  }

  @PutMapping(value = "/brands/{id}")
  public void update(@PathVariable Long id, @RequestBody BrandsDto d) {
    service.update(id, d);
  }

  @DeleteMapping(value = "/brands/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

}

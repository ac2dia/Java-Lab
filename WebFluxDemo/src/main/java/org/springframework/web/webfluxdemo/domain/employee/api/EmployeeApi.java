package org.springframework.web.webfluxdemo.domain.employee.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.webfluxdemo.domain.employee.application.EmployeeService;
import org.springframework.web.webfluxdemo.domain.employee.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/employees")
@RestController
public class EmployeeApi {

  private final EmployeeService employeeService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void create(@RequestBody Employee e) {
    employeeService.create(e);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") String id) {
    Mono<Employee> e = employeeService.findById(id);
    HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Mono<Employee>>(e, status);
  }

  @GetMapping(value = "/name/{name}")
  public ResponseEntity<Flux<Employee>> findByName(@PathVariable("name") String name) {
    Flux<Employee> e = employeeService.findByName(name);
    HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Flux<Employee>>(e, status);
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseEntity<Flux<Employee>> findAll() {
    Flux<Employee> emps = employeeService.findAll();
    HttpStatus status = emps != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Flux<Employee>>(emps, status);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public Mono<Employee> update(@RequestBody Employee e) {
    return employeeService.update(e);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable("id") String id) {
    employeeService.delete(id).subscribe();
  }

}

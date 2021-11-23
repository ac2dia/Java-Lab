package org.springframework.web.webfluxdemo.domain.employee.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.webfluxdemo.domain.employee.dao.EmployeeRepository;
import org.springframework.web.webfluxdemo.domain.employee.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepo;

  public void create(Employee e) {
    employeeRepo.save(e).subscribe();
  }

  public Mono<Employee> findById(String id) {
    return employeeRepo.findById(id);
  }

  public Flux<Employee> findByName(String name) {
    return employeeRepo.findByName(name);
  }

  public Flux<Employee> findAll() {
    return employeeRepo.findAll();
  }

  public Mono<Employee> update(Employee e) {
    return employeeRepo.save(e);
  }

  public Mono<Void> delete(String id) {
    return employeeRepo.deleteById(id);
  }
}

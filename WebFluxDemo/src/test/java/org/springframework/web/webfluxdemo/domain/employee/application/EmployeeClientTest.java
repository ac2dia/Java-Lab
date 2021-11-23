package org.springframework.web.webfluxdemo.domain.employee.application;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.webfluxdemo.domain.employee.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class EmployeeClientTest {

  public EmployeeClient employeeClient;

  @BeforeEach
  void setUp() throws IOException {
    String baseUrl = "http://localhost:8080";
    WebClient webClient = WebClient.create(baseUrl);

    employeeClient = new EmployeeClient(webClient);
  }

  @Test
  void create() {
    Employee e = new Employee();
    e.setName("ac");
    e.setSalary(3000L);

    Mono<Employee> employeeMono = employeeClient.create(e);
    System.out.println("create(): " + employeeMono.block());
  }

  @Test
  void findById() {
    String id = "18d7cfb6-bba2-489c-a53e-6dd80613063f";

    Mono<Employee> employeeMono = employeeClient.findById(id);
    System.out.println("findById(): " + employeeMono.block());
  }

  @Test
  void findByName() {
    String name = "shlee";

    Flux<Employee> employeeMono = employeeClient.findByName(name);
    System.out.println("findByName(): " + employeeMono.blockFirst());
  }

  @Test
  void findAll() {
    Flux<Employee> employeeFlux = employeeClient.findAll();
    System.out.println("findAll(): " + employeeFlux.blockFirst());
  }

  @Test
  void delete() {
    String id = "18d7cfb6-bba2-489c-a53e-6dd80613063f";

    employeeClient.delete(id);
  }
}
package org.springframework.web.webfluxdemo.domain.employee.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.webfluxdemo.domain.employee.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EmployeeClient {

  private final WebClient webClient;

  public Mono<Employee> create(Employee e) {
    return webClient.post()
        .uri("/employees")
        .body(Mono.just(e), Employee.class)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  public Mono<Employee> findById(String id) {
    return webClient.get()
        .uri("/employees/" + id)
        .retrieve()
        .onStatus(HttpStatus.NOT_FOUND::equals,
            clientResponse -> Mono.empty())
        .bodyToMono(Employee.class);
  }

  public Flux<Employee> findByName(String name) {
    return webClient.get()
        .uri("/employees/name/" + name)
        .retrieve()
        .onStatus(HttpStatus.NOT_FOUND::equals,
            clientResponse -> Mono.empty())
        .bodyToFlux(Employee.class);
  }

  public Flux<Employee> findAll() {
    return webClient.get()
        .uri("/employees")
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  public Mono<Employee> update(Employee e) {
    return webClient.put()
        .uri("/employees/" + e.getId())
        .body(Mono.just(e), Employee.class)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  public Mono<Void> delete(String id) {
    return webClient.delete()
        .uri("/employees/" + id)
        .retrieve()
        .bodyToMono(Void.class);
  }
}

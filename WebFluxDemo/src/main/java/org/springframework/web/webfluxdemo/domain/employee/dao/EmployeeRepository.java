package org.springframework.web.webfluxdemo.domain.employee.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.webfluxdemo.domain.employee.domain.Employee;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

  @Query("{ 'name': ?0 }")
  Flux<Employee> findByName(final String name);
}

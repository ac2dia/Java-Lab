package org.springframework.web.webfluxdemo.domain.employee.domain;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document(value = "employee")
public class Employee {

  @Id
  private String id = UUID.randomUUID().toString();

  private String name;

  private Long salary;

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", salary=" + salary +
        '}';
  }
}

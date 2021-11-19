package io.spring.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringSeucirtyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSeucirtyApplication.class, args);
  }

}

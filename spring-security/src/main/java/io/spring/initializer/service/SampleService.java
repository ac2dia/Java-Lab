package io.spring.initializer.service;

import io.spring.initializer.config.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  @Async
  public void asyncService() {
    SecurityLogger.log("Async Service");
    System.out.println("Async service is called");
  }
}

package org.springframework.web.webfluxdemo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer getPropertyPlaceholderConfigurer() {

    PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    pspc.setLocation(new ClassPathResource("application.yml"));
    pspc.setIgnoreUnresolvablePlaceholders(true);
    return pspc;
  }

}

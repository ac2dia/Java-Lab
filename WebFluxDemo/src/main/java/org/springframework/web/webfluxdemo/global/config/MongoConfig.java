package org.springframework.web.webfluxdemo.global.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackages = "org.springframework.web.webfluxdemo.domain.employee")
@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

  @Value("${spring.data.mongodb.username}")
  private String user;

  @Value("${spring.data.mongodb.password}")
  private String password;

  @Value("${spring.data.mongodb.database}")
  private String name;

  @Value("${spring.data.mongodb.port}")
  private String port;

  @Value("${spring.data.mongodb.host}")
  private String host;

  @Override
  public MongoClient reactiveMongoClient() {
    String connectionString = String.format("mongodb://%s:%s@%s:%s/%s",
        user, password, host, port, name);
    return MongoClients.create(connectionString);
  }

  @Override
  public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory databaseFactory,
      MappingMongoConverter mongoConverter) {
    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }

  @Override
  protected String getDatabaseName() {
    return this.name;
  }

}

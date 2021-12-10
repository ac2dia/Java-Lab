package com.example.springjwtwithredis.domain.account.dao;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class Token implements Serializable {

  private String username;
  private String accessToken;
  private String refreshToken;

}

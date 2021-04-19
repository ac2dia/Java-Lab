package org.springframework.amqp.config;

import org.springframework.stereotype.Component;

@Component
public class Constants {

  public static final String RED_QUEUE_NAME = "mq.red";
  public static final String BLUE_QUEUE_NAME = "mq.blue";
  public static final String EXCHANGE_NAME = "exchange";

}

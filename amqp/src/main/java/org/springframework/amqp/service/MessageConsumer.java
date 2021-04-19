package org.springframework.amqp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.config.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessageConsumer {

  @RabbitListener(bindings = @QueueBinding(value = @Queue(value = Constants.RED_QUEUE_NAME, durable = "true"),
      exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = "topic", durable = "true"),
      key = "key.red"))
  public void receiveMessageRed(final Message message) {
    log.info("consume message: '{}'", message.toString());
  }

  @RabbitListener(bindings = @QueueBinding(value = @Queue(value = Constants.BLUE_QUEUE_NAME, durable = "true"),
      exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = "topic", durable = "true"),
      key = "key.blue"))
  public void receiveMessageBlue(final Message message) {
    log.info("consume message: '{}'", message.toString()
    );
  }

}

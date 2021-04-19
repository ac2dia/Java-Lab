package org.springframework.amqp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.message.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MessageProducer {

  private final RabbitTemplate rabbitTemplate;
  private final TopicExchange topicExchange;

  public void sendMessage(String routingKey, CustomMessage message) {
    rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
  }

}

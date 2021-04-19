package org.springframework.amqp.service;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.message.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessageProducerTest {

  @Autowired
  private MessageProducer messageProducer;

  @Test
  void sendMessageTest() {
    CustomMessage redMessage = CustomMessage.builder()
        .text("I'll Love You!")
        .priority(0)
        .secret(false)
        .build();
    messageProducer.sendMessage("key.red", redMessage);

    CustomMessage blueMessage = CustomMessage.builder()
        .text("I'll Hate You!")
        .priority(0)
        .secret(false)
        .build();
    messageProducer.sendMessage("key.blue", blueMessage);
  }
}
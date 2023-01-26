package com.example.apache.activemq.producer;

import com.example.apache.activemq.annotation.QueueJmsTemplate;
import com.example.apache.activemq.model.InfoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMqProducer {

  private final JmsTemplate jmsTemplate;

  public ActiveMqProducer(@QueueJmsTemplate JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void send(final InfoMessage message) {
    log.info("Sending {}", message);
    jmsTemplate.convertAndSend("info-queue", message);
    log.info("Message {} sent", message);
  }
}

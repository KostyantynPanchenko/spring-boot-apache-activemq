package com.example.apache.activemq.consumer;

import com.example.apache.activemq.model.InfoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMqConsumer {

  @JmsListener(destination = "info-queue", containerFactory = "queueConnectionFactory")
  public void onMessage(final InfoMessage message) {
    log.info("Got a new message! {}", message.getMessage());
  }
}

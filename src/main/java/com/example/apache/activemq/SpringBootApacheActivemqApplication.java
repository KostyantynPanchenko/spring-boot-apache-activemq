package com.example.apache.activemq;

import com.example.apache.activemq.model.InfoMessage;
import com.example.apache.activemq.producer.ActiveMqProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApacheActivemqApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApacheActivemqApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(final ActiveMqProducer producer) {
    return (String[] args) ->
      producer.send(new InfoMessage("Welcome message", "We are up and running!"));
  }
}

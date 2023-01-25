package com.example.apache.activemq.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableConfigurationProperties(ActiveMQProperties.class)
class ActiveMqConfig {

  @Bean
  public ActiveMQConnectionFactory activeMQConnectionFactory(final ActiveMQProperties properties) {
    final var connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(properties.getBrokerUrl());
    connectionFactory.setUserName(properties.getUser());
    connectionFactory.setPassword(properties.getPassword());
    connectionFactory.setTrustedPackages(properties.getPackages().getTrusted());
    return connectionFactory;
  }

  @Bean
  public ConnectionFactory connectionFactory(
      final ActiveMQConnectionFactory factory, final ActiveMQProperties properties) {
    final var pooledConnectionFactory = new PooledConnectionFactory();
    pooledConnectionFactory.setConnectionFactory(factory);
    pooledConnectionFactory.setMaxConnections(properties.getPool().getMaxConnections());
    return pooledConnectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean(name = "queueConnectionFactory")
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      final ConnectionFactory connectionFactory) {
    final var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }

}

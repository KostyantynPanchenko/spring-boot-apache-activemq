package com.example.apache.activemq.config;

import com.example.apache.activemq.annotation.QueueJmsTemplate;
import com.example.apache.activemq.annotation.TopicJmsTemplate;
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
  @QueueJmsTemplate
  public JmsTemplate queueJmsTemplate(final ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean
  @TopicJmsTemplate
  public JmsTemplate topicJmsTemplate(final ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory);
  }

  @Bean
  public DefaultJmsListenerContainerFactory queueConnectionFactory(
      final ConnectionFactory connectionFactory) {
    final var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory topicDurableConnectionFactory(
      final ConnectionFactory connectionFactory) {
    final var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(true);
    factory.setClientId("durable-client-1");
    return factory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory topicNonDurableConnectionFactory(
      final ConnectionFactory connectionFactory) {
    final var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(false);
    factory.setClientId("nondurable-client-1");
    return factory;
  }
}

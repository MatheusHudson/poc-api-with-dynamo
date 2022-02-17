package br.com.poc.usercadastro.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

public class KafkaConfiguration {

  @Bean
  public NewTopic topic() {
    return new NewTopic("test_topic", 1, (short) 1);
  }

}
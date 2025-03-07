package codes.aliahmad.producer.wikimedia.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig
{
  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public ProducerFactory<String, String> producerFactory()
  {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // acks=0: Fire-and-forget, no acknowledgment from brokers (fast but unreliable).
    // acks=1: Leader acknowledges after writing to its log (default, good balance).
    // acks=all: All in-sync replicas must acknowledge (strongest durability)
    config.put(ProducerConfig.ACKS_CONFIG, "1");
    return new DefaultKafkaProducerFactory<>(config);
  }

  @Bean("defaultKafkaTemplate")
  @Primary
  public KafkaTemplate<String, String> kafkaTemplate()
  {
    return new KafkaTemplate<>(producerFactory());
  }
}

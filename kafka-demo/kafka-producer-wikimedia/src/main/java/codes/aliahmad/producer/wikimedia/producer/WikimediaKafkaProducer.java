package codes.aliahmad.producer.wikimedia.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WikimediaKafkaProducer implements WikimediaProducer
{
  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.topic.wikimedia-recent-change}")
  private String wikimediaTopic;


  @Override
  public void publishChangeEvent(String event)
  {
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(wikimediaTopic, event);
    kafkaTemplate.send(producerRecord)
            .thenAccept(result ->
                    log.info("Message sent successfully. Topic: {}, Partition: {}, Offset: {}",
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset())
            )
            .exceptionally(e -> {
              log.error("Error sending ride event: {}", event, e);
              return null;
            });
  }
}

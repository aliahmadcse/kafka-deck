package codes.aliahmad.producer.producer;


import codes.aliahmad.commons.dto.RideEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RideEventProducer
{
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  private final Faker faker = new Faker();

  @Value("${kafka.topic.rides-detail-events}")
  private String rideEventTopic;

  @Scheduled(fixedRate = 1000)
  public void sendRideEvent()
  {
    RideEvent rideEvent = createRideEvent();
    try
    {
      String message = objectMapper.writeValueAsString(rideEvent);

//      SendResult<String, String> response = kafkaTemplate
//              .send(rideEventTopic, rideEvent.getRideId().toString(), message)
//              .get();

//      RecordMetadata metadata = response.getRecordMetadata();
//      log.info("Message sent successfully. Topic: {}, Partition: {}, Offset: {}",
//              metadata.topic(),
//              metadata.partition(),
//              metadata.offset());

      ProducerRecord<String, String> record = new ProducerRecord<>(rideEventTopic, rideEvent.getRideId().toString(), message);
      kafkaTemplate.send(record)
              .thenAccept(result ->
                      log.info("Message sent successfully. Topic: {}, Partition: {}, Offset: {}",
                              result.getRecordMetadata().topic(),
                              result.getRecordMetadata().partition(),
                              result.getRecordMetadata().offset())
              )
              .exceptionally(e -> {
                log.error("Error sending ride event: {}", rideEvent, e);
                return null;
              });
    }
    catch (JsonProcessingException e)
    {
      log.error("Error serializing ride event: {}", rideEvent, e);
    }
//    catch (ExecutionException e)
//    {
//      log.error("Error sending ride event: {}", rideEvent, e);
//    }
//    catch (InterruptedException e)
//    {
//      log.error("Error sending ride event: {}", rideEvent, e);
//      Thread.currentThread().interrupt();
//    }
  }

  private RideEvent createRideEvent()
  {
    return new RideEvent(
            UUID.randomUUID(),
            Instant.now(),
            UUID.randomUUID(),
            faker.address().cityName(),
            faker.address().cityName(),
            faker.options().option("IN_PROGRESS", "COMPLETED", "CANCELLED"),
            UUID.randomUUID(),
            faker.bool().bool() ? Instant.now().plusSeconds(faker.number().numberBetween(300, 3600)) : null,
            BigDecimal.valueOf(faker.number().randomDouble(2, 50, 500))
    );
  }

}

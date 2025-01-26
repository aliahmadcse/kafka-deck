package codes.aliahmad.consumer.consumer;


import codes.aliahmad.consumer.port.RideEventProcessingPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RideEventConsumer
{
  private final RideEventProcessingPort rideEventProcessingPort;


  @KafkaListener(topics = "${kafka.topic.rides-detail-events}", groupId = "${spring.kafka.consumer.group-id}")
  public void rideEventListener(ConsumerRecord<String, String> consumerRecord)
  {
    String key = consumerRecord.key();
    String rideEvent = consumerRecord.value();
    long offset = consumerRecord.offset();
    int partition = consumerRecord.partition();
    log.info("Key: {}, Offset: {}, Partition: {}", key, offset, partition);

    rideEventProcessingPort.processRideEvent(rideEvent);
  }
}

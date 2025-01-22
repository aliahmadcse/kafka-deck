package codes.aliahmad.demo.consumer;


import codes.aliahmad.demo.port.RideEventProcessingPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RideEventConsumer
{
  private final RideEventProcessingPort rideEventProcessingPort;


  @KafkaListener(topics = "${kafka.topic.rides-detail-events}", groupId = "${spring.kafka.consumer.group-id}")
  public void rideEventListener(String rideEvent)
  {
    rideEventProcessingPort.processRideEvent(rideEvent);
  }
}

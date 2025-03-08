package codes.aliahmad.consumer.opensearch.consumer;

import codes.aliahmad.consumer.opensearch.service.OpenSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WikimediaRecentChangeConsumer
{

  private final OpenSearchService openSearchService;

  @KafkaListener(topics = "${kafka.topic.wikimedia-recent-change}", groupId = "${kafka.group-id.default}",
          containerFactory = "defaultListenerFactory")
  public void rideEventListener(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment)
  {
    try
    {
      openSearchService.indexWikimediaRecentChangeEvent(consumerRecord.value());
    }
    catch (Exception exception)
    {
      // ignoring exception for now, we should introduce a dead letter topic in kafka later
      log.error("Failed to process wikimedia recent change with message: {}", exception.getMessage(), exception);
    }
    acknowledgment.acknowledge();
  }
}

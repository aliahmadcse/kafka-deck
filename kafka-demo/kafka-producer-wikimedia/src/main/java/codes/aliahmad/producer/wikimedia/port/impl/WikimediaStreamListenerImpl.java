package codes.aliahmad.producer.wikimedia.port.impl;


import codes.aliahmad.producer.wikimedia.port.WikimediaStreamListener;
import codes.aliahmad.producer.wikimedia.producer.WikimediaProducer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class WikimediaStreamListenerImpl implements WikimediaStreamListener
{
  @Qualifier("wikimediaWebClient")
  private final WebClient webClient;

  private final WikimediaProducer wikimediaProducer;

  @Value("${wikimedia.change-stream-uri}")
  private String wikimediaStreamUri;


  @PostConstruct
  @Override
  public void startStreaming()
  {
    streamRecentChanges()
            .subscribeOn(Schedulers.boundedElastic()) // Run on a separate thread pool
            .subscribe(
                    wikimediaProducer::publishChangeEvent,
                    error -> log.error("Stream error: {}", error.getMessage(), error),
                    () -> log.info("Stream completed (this shouldn't happen)")
            );
  }

  private Flux<String> streamRecentChanges()
  {
    return webClient.get()
            .uri(wikimediaStreamUri)
            .retrieve()
            .bodyToFlux(String.class)
            .doOnNext(event -> log.info("Processing event: {}", event))
            .doOnError(error -> log.error("Error in stream: {}", error.getMessage(), error));
  }
}

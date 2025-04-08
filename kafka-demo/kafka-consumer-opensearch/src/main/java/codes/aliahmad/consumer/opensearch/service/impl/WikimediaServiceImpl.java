package codes.aliahmad.consumer.opensearch.service.impl;

import codes.aliahmad.consumer.opensearch.repository.IndexRepository;
import codes.aliahmad.consumer.opensearch.repository.WikimediaRepository;
import codes.aliahmad.consumer.opensearch.service.WikimediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.indices.IndexCreationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikimediaServiceImpl implements WikimediaService
{
  private static final String INDEX_NAME = "wikimedia";
  private final WikimediaRepository wikimediaRepository;
  private final IndexRepository indexRepository;


  @Override
  public void indexWikimediaRecentChangeEvent(String event)
  {
    log.info("Indexing wikimedia recent change event: {}", event);
    try
    {
      wikimediaRepository.indexEvent(INDEX_NAME, event);
    }
    catch (Exception e)
    {
      log.error("Failed to index wikimedia recent change event: {}", event, e);
    }
  }


  @Override
  public Optional<CreateIndexResponse> createIndexIfNotExists()
  {
    // Check if index already exists
    CreateIndexResponse createIndexResponse = null;
    try
    {
      boolean exists = indexRepository.exist(INDEX_NAME);
      if (!exists)
      {
        createIndexResponse = indexRepository.createIndex(INDEX_NAME);
      }
    }
    catch (IOException exception)
    {
      log.error("Failed to create index: {}", exception.getMessage(), exception);
      throw new IndexCreationException("Failed to create index: " + INDEX_NAME, exception);
    }
    return Optional.ofNullable(createIndexResponse);
  }

}

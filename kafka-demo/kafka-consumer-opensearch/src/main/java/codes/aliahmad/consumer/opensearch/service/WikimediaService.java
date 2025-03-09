package codes.aliahmad.consumer.opensearch.service;

import org.opensearch.client.indices.CreateIndexResponse;

import java.util.Optional;

public interface WikimediaService
{
  void indexWikimediaRecentChangeEvent(String event);

  Optional<CreateIndexResponse> createIndexIfNotExists();
}

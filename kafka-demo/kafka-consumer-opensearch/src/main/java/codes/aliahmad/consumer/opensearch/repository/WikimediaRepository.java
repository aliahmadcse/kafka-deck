package codes.aliahmad.consumer.opensearch.repository;


import org.opensearch.action.index.IndexResponse;

import java.io.IOException;

public interface WikimediaRepository
{
  IndexResponse indexEvent(String eventName, String event, String id) throws IOException;
}

package codes.aliahmad.consumer.opensearch.repository;

import org.opensearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public interface IndexRepository
{
  boolean exist(String indexName) throws IOException;

  CreateIndexResponse createIndex(String indexName) throws IOException;
}

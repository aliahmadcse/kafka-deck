package codes.aliahmad.consumer.opensearch.repository.impl;

import codes.aliahmad.consumer.opensearch.repository.IndexRepository;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class IndexRepositoryImpl implements IndexRepository
{
  private final RestHighLevelClient client;

  @Override
  public boolean exist(String indexName) throws IOException
  {
    GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
    return client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
  }

  @Override
  public CreateIndexResponse createIndex(String indexName) throws IOException
  {
    CreateIndexRequest request = new CreateIndexRequest(indexName);
    return client.indices().create(request, RequestOptions.DEFAULT);
  }
}

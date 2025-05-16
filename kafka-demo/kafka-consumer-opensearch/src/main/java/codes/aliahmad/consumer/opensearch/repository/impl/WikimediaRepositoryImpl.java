package codes.aliahmad.consumer.opensearch.repository.impl;

import codes.aliahmad.consumer.opensearch.repository.WikimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WikimediaRepositoryImpl implements WikimediaRepository
{
  private final RestHighLevelClient client;


  @Override
  public IndexResponse indexEvent(String indexName, String event, String id) throws IOException
  {
    IndexRequest indexRequest = new IndexRequest(indexName)
            .source(event, XContentType.JSON)
            .id(id);

    return client.index(indexRequest, RequestOptions.DEFAULT);
  }
}

package codes.aliahmad.consumer.opensearch.service;

import codes.aliahmad.consumer.opensearch.document.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

  private final RestHighLevelClient client;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private static final String INDEX_NAME = "books";



  public void createDocumentsIndex() throws IOException {
    // Check if index already exists
    GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);
    boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

    if (!exists) {
      CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);
      client.indices().create(request, RequestOptions.DEFAULT);
    }
  }

  public String indexDocument(Book book) throws IOException {
    // Create the index if it doesn't exist
    createDocumentsIndex();

    // Convert document to map
    Map<String, Object> jsonMap = objectMapper.convertValue(book, Map.class);

    // Create index request
    IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
            .id(book.getId())
            .source(jsonMap, XContentType.JSON);

    // Execute the request
    IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

    return response.getId();
  }

  public List<Book> searchDocuments(String searchTerm) throws IOException
  {
    // Build search query
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.multiMatchQuery(searchTerm, "title", "content"));

    // Create search request
    SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
    searchRequest.source(sourceBuilder);

    // Execute search
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

    // Process results
    List<Book> documents = new ArrayList<>();
    for (SearchHit hit : searchResponse.getHits().getHits()) {
      Book document = objectMapper.convertValue(hit.getSourceAsMap(), Book.class);
      document.setId(hit.getId());
      documents.add(document);
    }

    return documents;
  }

  public void deleteIndex() throws IOException {
    GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);
    boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

    if (exists) {
      DeleteIndexRequest request = new DeleteIndexRequest(INDEX_NAME);
      client.indices().delete(request, RequestOptions.DEFAULT);
    }
  }
}

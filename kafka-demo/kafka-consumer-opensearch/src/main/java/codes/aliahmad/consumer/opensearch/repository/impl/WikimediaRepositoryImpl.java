package codes.aliahmad.consumer.opensearch.repository.impl;

import codes.aliahmad.consumer.opensearch.repository.WikimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WikimediaRepositoryImpl implements WikimediaRepository
{
  private final RestHighLevelClient client;




}

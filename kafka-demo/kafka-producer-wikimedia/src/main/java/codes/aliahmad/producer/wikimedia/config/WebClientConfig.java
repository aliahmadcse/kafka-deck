package codes.aliahmad.producer.wikimedia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{
  @Value("${wikimedia.base-uri}")
  private String wikimediaBaseUri;

  @Bean("wikimediaWebClient")
  public WebClient webClient()
  {
    return WebClient.builder()
            .baseUrl(wikimediaBaseUri)
            .build();
  }
}

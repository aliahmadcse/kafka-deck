package codes.aliahmad.consumer.opensearch.config;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClientBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@Configuration
public class OpenSearchConfig
{
  @Value("${opensearch.host}")
  private String host;

  @Value("${opensearch.port}")
  private int port;

  @Value("${opensearch.protocol}")
  private String protocol;

  @Value("${opensearch.username:}")
  private String username;

  @Value("${opensearch.password:}")
  private String password;

  @Value("${opensearch.connection-timeout:5000}")
  private int connectionTimeout;

  @Value("${opensearch.socket-timeout:60000}")
  private int socketTimeout;

  @Value("${opensearch.ssl.verification-mode:full}")
  private String sslVerificationMode;


  @Bean
  public RestHighLevelClient openSearchClient()
  {
    RestClientBuilder builder = RestClient.builder(
            new HttpHost(host, port, protocol)
    );

    // Configure timeouts
    builder.setRequestConfigCallback(requestConfigBuilder ->
            requestConfigBuilder
                    .setConnectTimeout(connectionTimeout)
                    .setSocketTimeout(socketTimeout)
    );


    builder.setHttpClientConfigCallback(httpClientBuilder -> {
      try
      {
        httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        httpClientBuilder.setSSLContext(sslContext());

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

        return httpClientBuilder;
      }
      catch (Exception e)
      {
        throw new RuntimeException("Failed to configure SSL context", e);
      }
    });


    return new RestHighLevelClient(builder);
  }


  private SSLContext sslContext() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException
  {
    SSLContext sslContext;
    if ("none".equals(sslVerificationMode))
    {
      // Development only - trust all certificates
      sslContext = new SSLContextBuilder()
              .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
              .build();
    }
    else
    {
      // Use system default SSL context for production
      sslContext = SSLContext.getDefault();
    }
    return sslContext;
  }

}

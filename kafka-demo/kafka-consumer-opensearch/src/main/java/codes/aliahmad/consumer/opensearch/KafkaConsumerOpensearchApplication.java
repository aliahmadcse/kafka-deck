package codes.aliahmad.consumer.opensearch;

import codes.aliahmad.consumer.opensearch.service.WikimediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class KafkaConsumerOpensearchApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(KafkaConsumerOpensearchApplication.class, args);
  }


  @Component
  @RequiredArgsConstructor
  static class MyCommandLineRunner implements CommandLineRunner
  {
    private final WikimediaService wikimediaService;

    @Override
    public void run(String... args)
    {
      wikimediaService.createIndexIfNotExists();
    }
  }
}

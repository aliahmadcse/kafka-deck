package codes.aliahmad.consumer.opensearch.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book
{
  private String id;
  private String title;
  private String content;
  private String author;
  private String createdAt;
}

package codes.aliahmad.consumer.opensearch.stream;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WikiChangeEvent {
    private boolean bot;
    private String user;
    private String title;
    // Add more fields if needed
}

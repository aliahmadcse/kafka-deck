package codes.aliahmad.consumer.opensearch.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;


/**
 * Kafka Streams configuration for processing Wikipedia edit events.
 */
@Slf4j
@Configuration
@EnableKafkaStreams
public class BotEditStream {

    @Bean
    public KStream<String, WikiChangeEvent> processStream(StreamsBuilder builder) {
        KStream<String, WikiChangeEvent> stream = builder.stream("wikimedia-recent-change",
                Consumed.with(Serdes.String(), new JsonSerde<>(WikiChangeEvent.class)));

        stream.filter((key, event) -> event.isBot())
                .groupBy((key, value) -> "bot", Grouped.with(Serdes.String(), new JsonSerde<>(WikiChangeEvent.class)))
                .count(Materialized.as("bot-edit-counts"))
                .toStream()
//                each pod will count its own bot edits, so we need to aggregate them and that's where flink shines
                .foreach((key, count) -> log.info("Bot edits: {}", count));

        return stream;
    }
}

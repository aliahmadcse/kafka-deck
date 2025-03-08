## Wikipedia Kafka Producer

This module continuously pulls an events stream from Wikimedia changelog and publishes it to kafka topic

### Useful Links

- [Stream API](https://stream.wikimedia.org/v2/stream/recentchange)
- [Observability](https://codepen.io/Krinkle/pen/BwEKgW)
- [Observability](https://esjewett.github.io/wm-eventsource-demo/)
- [Stream API Doc](https://stream.wikimedia.org/?doc#/streams/get_v2_stream_recentchange)

## Setting up

- You must spin up a Kafka broker, see docker compose present at the project root
- Run the command `docker-compose up -d` to spin up Kafka locally on docker

## Running

- Run the command `mvn spring-boot:run` to start the Kafka Producer Module

## Impact

- The Wikipedia Kafka Producer Module will start pulling events from the Wikimedia changelog and publish them to Kafka topic named `wikimedia-recent-change`


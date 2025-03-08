# Kafka Consumer Opensearch

This module consumes events from wikimedia-recent-change kafka topic and sends it to opensearch.

## Setting up
- Spin up the opensearch cluster using the command `docker compose -f docker-compose-opensearch.yml up -d`
- You must spin up a Kafka broker, see docker compose present at the project root
- Run the command `docker-compose up -d` to spin up Kafka locally on docker

## Running
- Run the command `mvn spring-boot:run` to start the Kafka Consumer Opensearch Module

## Impact
- The Kafka Consumer Opensearch Module will start consuming events from the wikimedia-recent-change kafka topic and send it to opensearch
- The events will be indexed in opensearch and can be queried using opensearch API
- The Kafka Consumer Opensearch Module will also send the events to a dead letter topic in kafka if any error occurs during processing


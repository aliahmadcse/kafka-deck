# Kafka Producer

This module demonstrate working with a Kafka Java Producer and uses Java faker library to generate random event and
publish them to a Kafka topic

## Setting up

- You must spin up a Kafka node, see docker compose present at the project root
- Run the command `docker-compose up -d` to spin up Kafka locally on docker

## Running

- Run the command `mvn spring-boot:run` to start the Kafka Producer Module

## Impact

- The Kafka Producer Module will start publishing events to the Kafka topic named `rides-events`


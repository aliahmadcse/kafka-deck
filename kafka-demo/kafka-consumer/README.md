# Kafka Consumer

Kafka Consumer Module consumes the published events from the Kafka Producer module and writes them to Cassandra

## Setup

- You must spin up a Cassandra node, see docker compose present at the project root
- Run the command `docker compose up -d` to spin up Kafka and Cassandra locally on docker
- Set the database using the `db.cql` file present in [Resources](./src/main/resources/db/db.cql`)
- Run the Kafka Consumer Module and Kafka Producer Module simultaneously

## Running

- Run the command `mvn spring-boot:run` to start the Kafka Consumer Module

## Impact

- The Kafka Consumer Module will start consuming events from the Kafka Producer Module and write them to Cassandra
- The events will be written to the `rides_details` table in the `easy_taxi` keyspace

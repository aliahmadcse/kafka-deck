---
marp: true
theme: default
_class: lead
author: Ali Ahmad
paginate: true
backgroundColor: #ffffff
# backgroundImage: url('https://marp.app/assets/hero-background.svg')
backgroundImage: url('./assets/background-new.jpg')
---


# **Fundamentals of Apache Kafka**

---

## **Data Integration**
![bg right:60% 40%](./assets/simple-data-integration.svg)

---

## **Data Integration**
* Extract the data, transform it and Load it to the target system
* Simple at first

---

## **Data Integration Evolution**
![bg right:70% 100%](./assets/complex-data-integration.png)

---
## **Data Integration Evolution**
* If you have `4 source systems` and `6 target systems`, you need to write `24 integrations`
* Cherry on Top
  - Each Integration comes with diffulties around
    - Protocol - how the data is transported (TCP, HTTP, GRPC, FTP, JDBC)
    - Data format - how the data is parsed (Binary, CSV, JSON, Avro, Protobuf)

---

## **Data Integration**

![bg right:70% 90%](./assets/data-integration-kafka.svg)

---

## **Data Integration**
![bg right:70% 90%](./assets/data-integration-example.png)

---

## **Why Kafka**
> Kafka is a `distributed`, `fault tolerant`, `horizontly Scalable`, pub-sub event streaming platform designed for handling real-time data feeds, Created by LinkedIn, Open Sourced under Apache License 2.0, Maintained by Confluent, IBM, Cloudera and LinkedIn

---
## **Why Kafka**
* Can scale to 100s of brokers
* Can scale to millions of messages per second
* High performance (latency of less than 10ms) - real time
* Used has a huge community support and used by large tech organizations around the world
* Integration with Spark, Flink, Storm, Hadoop, and other Big Data technologies
* De-coupling of system dependencies
* Application Logs gathering

## **Agenda**
* Kafka Cluster
* Kafka Broker
* Producers and Source Systems
* Consumers and Target Systems
* Kafka Management (ZooKeeper or KRaft Mode)

---
## **Agenda**
![bg right:75% 100%](./assets/agenda.png)

---
## **Agenda**
* Kafka Connect
* Kafka Stream API
* KsqlDB
* Confluent Schema Registry (Confluent Components)
* Kafka Architecture in the enterprise
* Real world use cases
* Advanced API + Configurations
* Topic Configurations

---

## **Agenda** (Opeations Perspective)
* Kafka Security
* Kafka Monitoring and Operations
* Kafka Cluster Setup and Administration
---

## **Kafka Topics**


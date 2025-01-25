---
marp: true
theme: default
_class: lead
author: Ali Ahmad
paginate: true
backgroundColor: #ffffff
# backgroundImage: url('https://marp.app/assets/hero-background.svg')
backgroundImage: url('./assets/01/background-new.jpg')
---


# **Utilities**

---


## **Utilities**
* Kafka Utilities comes bundled with Kafka binary

---

## **kafka-topics.sh**

- Create a topic
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --topic first-topic --create --partitions 3 --replication-factor 2

```
---
## **kafka-topics.sh**
- List topics

```bash
kafka-topics.sh --bootstrap-server localhost:9092 --list 

```

---

## **Kafka-topics.sh**
- Describe a topic
```shell

kafka-topics.sh --bootstrap-server localhost:9092 --topic first-topic --describe
```

---

## **Kafka-topics.sh**

- Delete a topic
- (only works if `delete.topic.enable=true`)

```shell
kafka-topics.sh --bootstrap-server localhost:9092 --topic first-topic --delete
```
---

## **kafka-console-producer.sh**
- Producing messages on the command line

```shell
kafka-console-producer --bootstrap-server localhost:9092 --topic first-topic --producer-property acks=all --property parse.key=true --property key.separator=:

```

>example key:example value
>name:Stephane
---

## **kafka-console-consumer.sh**

```shell
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first-topic 
--formatter org.apache.kafka.tools.consumer.DefaultMessageFormatter 
--property print.timestamp=true --property print.key=true --property print.value=true --from-beginning
```

---

## **kafka-console-consumer.sh**
- Consuming messages in consumer-group

```bash
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first-topic
```

```bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first-topic --group my-first-application

```

```bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first-topic --group my-second-application --from-beginning
```
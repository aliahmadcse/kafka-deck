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

# **Questions**

---

## **Can we reset the offset for a consumer?**

* Yes, we can

```bash
kafka-consumer-groups.sh --bootstrap-server localhost:9092 
  --group my-consumer-group 
  --reset-offsets 
  --topic my-topic:0 # Specifies exactly partition 0
  --to-offset 1000
```
---

## **Are Kafka's Consumer and Producer APIs Http based?**

* No, Kafka uses a binary protocol over TCP called `Kafka Binary Protocol`
* A lot of optimization, like a single long lived connection to a broker from a client, Multiplexing, maintaining messages publish order, etc
* Resource [Kafka Binary Protocol](https://kafka.apache.org/protocol)

---

## **If Kafka writes messages on the disk, how does it maintain low latency?**
* **Producer**

  * Kafka writes messages sequentially to disk rather than random write
  * Modern hard drives and SSDs can handle sequential writes at nearly the speed of memory
  * Write ahead log is maintained for each message that arrives at the broker and it is written to the `OS Page Cache` first
  * Kafka keeps maintaing messages in memory until a certain number (configurebale batch), and this is written as a batch to the disk to reduce the I/O cost


---


## **If Kafka writes messages on the disk, how does it maintain low latency?**
*  **Consumer**
  * While consuming, messages are read from the disk using `Zero Copy Architecture` (uses the sendfile() system call on Linux. This allows Kafka to transfer data from the disk to the network socket without copying it into user space (application memory))

---

## **Topic Internals, is it a queue per subscriber?**
* Yes and No
* Each partition behaves like a queue where only one consumer processes messages from that partition.
* Different consumer groups can independently consume the same topic without affecting each other, `unlike a typical queue system`
* In true essense, a topic partition is nothing but an `append only log file` with the offset maintained
* [Partition](https://github.com/apache/kafka/blob/trunk/core/src/main/scala/kafka/cluster/Partition.scala)

---
## **Increasing or decreasing the number of partitions?**

* **Decreasing Partitions**

  * This is more complicated and generally not recommended
  * Kafka doesn't support direct partition reduction
  * Effectively impossible without data loss or complex workarounds

* **Increasing Partitions**

  * Yes, you can increase the number of partitions for a topic
  * This is done dynamically while the topic is active
  * Existing messages don't automatically move
  * New messages will use the additional partitions

---
## **Increasing or decreasing the number of partitions?**

* **Increasing Partitions**
    * Existing consumer won't start consuming from new partition, unless a re-balance occurs (adding or removing a consumer) or we can manually trigger a re-balance
    * Or just re-start the app
    * Re starting the app, reminds me that Idempotency is very crucial on the client side with Kafka, if the consumer dies before committing the offset, we may re-process the message and send the update to the database.


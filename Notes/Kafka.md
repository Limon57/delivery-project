# 📚 Kafka Cheat Sheet (Implementation-Focused)

A practical, implementation-ready guide to using Apache Kafka. From starting services to real-world usage examples in Java/Python, CLI commands, Docker setups, and monitoring.

---

## 📖 Table of Contents

- [🚀 Getting Started with Kafka](#-getting-started-with-kafka)
- [⚙️ Kafka Core Concepts (Quick Summary)](#️-kafka-core-concepts-quick-summary)
- [🛠️ Setting Up Kafka Locally (Minimal Setup)](#️-setting-up-kafka-locally-minimal-setup)
- [📦 Kafka Installation and Configuration Files](#-kafka-installation-and-configuration-files)
- [📡 Producing and Consuming Messages (Console Tools)](#-producing-and-consuming-messages-console-tools)
- [🧵 Topics, Partitions, and Offsets](#-topics-partitions-and-offsets)
- [🧰 Kafka CLI Commands (Quick Tasks)](#-kafka-cli-commands-quick-tasks)
- [🧪 Testing with Kafka Console Tools](#-testing-with-kafka-console-tools)
- [🔄 Kafka Consumer Groups](#-kafka-consumer-groups)
- [🧱 Kafka Brokers and Clusters](#-kafka-brokers-and-clusters)
- [🔧 Config Options (Examples)](#-config-options-examples)
- [📂 Kafka Topics: Create, List, Delete](#-kafka-topics-create-list-delete)
- [📥 Kafka Producer Examples (Java / Python)](#-kafka-producer-examples-java--python)
- [📤 Kafka Consumer Examples (Java / Python)](#-kafka-consumer-examples-java--python)
- [📊 Kafka Monitoring and Logging](#-kafka-monitoring-and-logging)
- [📡 Kafka with Docker and Docker Compose](#-kafka-with-docker-and-docker-compose)
- [🔌 Kafka Connect](#-kafka-connect)
- [🪝 Kafka Streams Basics](#-kafka-streams-basics)
- [⚡ Kafka vs Traditional Messaging Systems](#-kafka-vs-traditional-messaging-systems)
- [💥 Handling Failures and Message Replays](#-handling-failures-and-message-replays)
- [🔐 Kafka Security](#-kafka-security)
- [🌍 Kafka Use Cases (Real-World Examples)](#-kafka-use-cases-real-world-examples)
- [🧹 Cleanup and Maintenance Commands](#-cleanup-and-maintenance-commands)
- [📄 Common Troubleshooting Scenarios](#-common-troubleshooting-scenarios)

---

## 🚀 Getting Started with Kafka

| Step         | Command / Action |
|--------------|------------------|
| Check version | `kafka-topics.sh --version` |
| Run ZooKeeper | `bin/zookeeper-server-start.sh config/zookeeper.properties` |
| Run Kafka     | `bin/kafka-server-start.sh config/server.properties` |

---

## ⚙️ Kafka Core Concepts (Quick Summary)

| Component  | Role                          |
|------------|-------------------------------|
| Producer   | Sends messages to topics      |
| Consumer   | Reads messages from topics    |
| Topic      | Channel for messages          |
| Broker     | Kafka server handling data    |
| Partition  | Splits topic for scalability  |
| Offset     | Unique ID for each message    |

---

## 🛠️ Setting Up Kafka Locally (Minimal Setup)

```bash
# 1. Start ZooKeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# 2. Start Kafka Broker
bin/kafka-server-start.sh config/server.properties
```
> 💡 Use different terminals for each process.

---

## 📦 Kafka Installation and Configuration Files

| File                 | Purpose                        |
|----------------------|--------------------------------|
| `server.properties`  | Broker settings (ports, IDs)   |
| `consumer.properties`| Consumer-specific configs      |
| `producer.properties`| Producer-specific configs      |

---

## 📡 Producing and Consuming Messages (Console Tools)

```bash
# Start producer
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092

# Start consumer
bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
```

---

## 🧵 Topics, Partitions, and Offsets

| Task          | Command Example |
|---------------|-----------------|
| Create topic  | `kafka-topics.sh --create --topic my-topic --partitions 3 --replication-factor 1` |
| List topics   | `kafka-topics.sh --list --bootstrap-server localhost:9092` |
| Describe topic| `kafka-topics.sh --describe --topic my-topic` |

---

## 🧰 Kafka CLI Commands (Quick Tasks)

```bash
# List consumer groups
kafka-consumer-groups.sh --list --bootstrap-server localhost:9092

# View messages with offsets
kafka-console-consumer.sh --topic my-topic --offsets --from-beginning
```

---

## 🧪 Testing with Kafka Console Tools

| Tool             | Usage                        |
|------------------|------------------------------|
| Console Producer | Manually send test messages  |
| Console Consumer | View messages in real-time   |
| Kafka Cat (kcat) | JSON-friendly consumption    |

---

## 🔄 Kafka Consumer Groups

- Consumers in the same **group.id** share partitions.
- Replay messages with `--from-beginning`.

---

## 🧱 Kafka Brokers and Clusters

| Scenario   | What to Do |
|------------|------------|
| Add broker | Unique `broker.id`, update `server.properties` |
| Multi-node | Same `zookeeper.connect` on all brokers        |

---

## 🔧 Config Options (Examples)

**Producer Settings**
- `acks=all` — Wait for all replicas
- `retries=3` — Retry if fails
- `batch.size=16384` — Max batch size

**Consumer Settings**
- `group.id=app-group` — Assign group
- `auto.offset.reset=earliest` — Read from beginning if no offset
- `enable.auto.commit=false` — Manual offset commits

---

## 📂 Kafka Topics: Create, List, Delete

```bash
# Create
kafka-topics.sh --create --topic orders --partitions 2 --replication-factor 1

# List
kafka-topics.sh --list

# Delete
kafka-topics.sh --delete --topic orders
```

---

## 📥 Kafka Producer Examples (Java / Python)

**Java**
```java
ProducerRecord<String, String> record = new ProducerRecord<>("topic", "key", "value");
producer.send(record);
```

**Python**
```python
from kafka import KafkaProducer
producer = KafkaProducer(bootstrap_servers='localhost:9092')
producer.send('topic', b'hello world')
```

---

## 📤 Kafka Consumer Examples (Java / Python)

**Java**
```java
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList("topic"));
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
}
```

**Python**
```python
from kafka import KafkaConsumer
consumer = KafkaConsumer('topic', bootstrap_servers='localhost:9092')
for message in consumer:
    print(message.value)
```

---

## 📊 Kafka Monitoring and Logging

| Tool         | Purpose                        |
|--------------|--------------------------------|
| Prometheus   | Scrape Kafka metrics           |
| Grafana      | Visualize metrics dashboards   |
| JMX Exporter | Export Kafka JVM metrics       |

---

## 📡 Kafka with Docker and Docker Compose

```yaml
services:
  zookeeper:
    image: zookeeper
    ports: ["2181:2181"]

  kafka:
    image: bitnami/kafka
    ports: ["9092:9092"]
    environment:
      KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_CFG_LISTENERS: PLAINTEXT://:9092
```

---

## 🔌 Kafka Connect

| Feature         | Example Use                    |
|-----------------|--------------------------------|
| Source Connectors | Import from MySQL, PostgreSQL |
| Sink Connectors   | Export to S3, Elasticsearch   |
| REST Config       | `POST /connectors` with JSON  |

---

## 🪝 Kafka Streams Basics

Use Kafka Streams API to process messages in Java apps.

💡 Example: Count events per key every 5 seconds.

---

## ⚡ Kafka vs Traditional Messaging Systems

| Feature         | Kafka     | Traditional MQ |
|-----------------|-----------|----------------|
| Durability      | High      | Medium         |
| Speed           | Very Fast | Slower         |
| Replay Support  | Yes       | Rarely         |
| Scalability     | Easy      | Limited        |

---

## 💥 Handling Failures and Message Replays

- Use `--from-beginning` to reprocess topics
- Set retries, handle dead-letter queues manually
- Kafka auto-replicates partitions for durability

---

## 🔐 Kafka Security

| Feature | Description                             |
|---------|-----------------------------------------|
| SSL     | Encrypt client-broker communication     |
| SASL    | Auth (PLAIN, SCRAM, GSSAPI, etc.)       |
| ACLs    | Restrict access to topics and actions   |

---

## 🌍 Kafka Use Cases (Real-World Examples)

- Microservice communication (e.g., Uber, Netflix)
- Website clickstream tracking (LinkedIn)
- IoT sensor ingestion
- Fraud detection in banking
- Log aggregation / replacement for syslog

---

## 🧹 Cleanup and Maintenance Commands

```bash
# Delete old topic
kafka-topics.sh --delete --topic old-topic

# View consumer group lag
kafka-consumer-groups.sh --describe --group my-group

# Inspect log directories
kafka-log-dirs.sh --describe --bootstrap-server localhost:9092
```

---

## 📄 Common Troubleshooting Scenarios

| Problem                  | Fix or Reason                                  |
|--------------------------|------------------------------------------------|
| Broker not available     | Ensure Kafka is running and port is exposed    |
| High consumer lag        | Optimize code or scale up consumers            |
| Messages missing         | Wrong topic/broker/partition or offset reset   |
| Docker networking issues | Use correct `KAFKA_ADVERTISED_LISTENERS`      |

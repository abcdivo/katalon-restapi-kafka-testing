# Katalon API Kafka Automation

End-to-end test automation project using Katalon Studio for REST API testing and Apache Kafka consumer validation.

## Overview

This project demonstrates:

* REST API automation testing using Katalon Studio
* Kafka consumer validation using Apache Kafka
* Docker-based Kafka environment
* Integration testing between API and Kafka messaging

## Tech Stack

* Katalon Studio 11
* Apache Kafka
* Docker Desktop
* Groovy
* Kafka Client 3.5.1

## Project Structure

```text
katalon-api-kafka-automation/
│
├── Test Cases/
│   ├── API/
│   │   ├── TC_Create_Post
│   │   └── TC_Get_Post
│   │
│   └── Kafka/
│       └── TC_Kafka_Consumer
│
├── Keywords/
│   └── kafka/
│       └── KafkaConsumerUtil.groovy
│
├── Object Repository/
├── Test Suites/
│   └── TS_API_Kafka
│
├── Profiles/
├── docker-compose.yml
└── README.md
```

## Prerequisites

* Java 17 or above
* Katalon Studio 11
* Docker Desktop
* Git

## Start Kafka

Run Kafka and Zookeeper using Docker Compose:

```bash
docker compose up -d
```

Verify containers:

```bash
docker ps
```

## Create Kafka Topic

Enter Kafka container:

```bash
docker exec -it kafka bash
```

Create topic:

```bash
kafka-topics \
--create \
--topic orders \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1
```

Verify topic:

```bash
kafka-topics --list --bootstrap-server localhost:9092
```

## Produce Test Message

```bash
kafka-console-producer \
--broker-list localhost:9092 \
--topic orders
```

Example message:

```text
ORDER
```

## Kafka Consumer Validation

Kafka consumer implementation:

```text
Keywords/kafka/KafkaConsumerUtil.groovy
```

Validation example:

```groovy
String msg = KafkaConsumerUtil.consumeMessage()

assert msg != null
assert !msg.isEmpty()
assert msg.contains("ORDER")
```

## Run Test Suite

Open project in Katalon Studio.

Execute:

```text
Test Suites
└── TS_API_Kafka
```

Or run individual test cases:

* TC_Create_Post
* TC_Get_Post
* TC_Kafka_Consumer

## Expected Result

* REST API requests return successful responses.
* Kafka consumer reads messages from topic `orders`.
* All assertions pass successfully.

## Author

QA Automation Engineering Practice Project

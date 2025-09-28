# spring-kafka-integration

> A compact Spring Boot example demonstrating producer/consumer patterns using Apache Kafka. Ideal for learning, prototyping, and including in a portfolio.

---

## Table of Contents

* [About](#about)
* [Features](#features)
* [Tech Stack](#tech-stack)
* [Prerequisites](#prerequisites)
* [Getting Started (Local)](#getting-started-local)

  * [1. Clone the repo](#1-clone-the-repo)
  * [2. Run Kafka (Docker Compose)](#2-run-kafka-docker-compose)
  * [3. Configure application properties](#3-configure-application-properties)
  * [4. Build & run](#4-build--run)
* [Usage](#usage)
* [Project Structure](#project-structure)
* [Extending / Ideas](#extending--ideas)
* [Contributing](#contributing)
* [License](#license)

---

## About

`spring-kafka-integration` is a small, well-documented Spring Boot project that shows how to set up Kafka producers and consumers using `spring-kafka`. It includes example message models, serialization (JSON), basic error handling, and a minimal web endpoint to send test messages.

## Features

* Kafka producer and consumer examples
* JSON serialization/deserialization for messages
* Topic auto-creation (configurable)
* Simple REST endpoint to publish messages
* Docker Compose file for quick Kafka + Zookeeper setup

## Tech Stack

* Java 17+
* Spring Boot
* spring-kafka
* Apache Kafka
* Maven
* Docker & Docker Compose (for local Kafka)

## Prerequisites

* Java JDK 17 or later
* Maven 3.6+
* Docker & Docker Compose (optional but recommended for local Kafka)

## Getting Started (Local)

### 1. Clone the repo

```bash
git clone https://github.com/param7666/spring-kafka-integration.git
cd spring-kafka-integration
```

### 2. Run Kafka (Docker Compose)

A `docker-compose.yml` is included for a quick local Kafka + Zookeeper setup:

```bash
docker-compose up -d
```

This starts Zookeeper and Kafka on their default Docker network. If you already run Kafka locally, skip this step and adjust `spring.kafka.bootstrap-servers` accordingly.

### 3. Configure application properties

Edit `src/main/resources/application.yml` (or `application.properties`) if you need to change the Kafka bootstrap server, topic names, or serializers.

Example minimal config:

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: spring-kafka-demo-group
      auto-offset-reset: earliest
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    consumer:
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
```

> Tip: If using `JsonDeserializer`, configure `spring.kafka.consumer.properties.spring.json.trusted.packages` or set it to `*` for local testing.

### 4. Build & run

Build the project with Maven and start the application:

```bash
mvn clean package
java -jar target/spring-kafka-demo-0.0.1-SNAPSHOT.jar
```

If you use your IDE (IntelliJ/Eclipse), run the `SpringKafkaDemoApplication` main class.

## Usage

* Send a test message via REST (example):

```http
POST /api/messages
Content-Type: application/json

{"id":1, "text":"Hello, Kafka from Spring!"}
```

* Check application logs to see consumer output. You can also use Kafka CLI tools or `kafkacat` to view topic messages.

## Project Structure

```
src/
├─ main/
│  ├─ java/com/example/springkafkademo/
│  │  ├─ config/       # Kafka configuration (producer/consumer factories, topics)
│  │  ├─ controller/   # REST endpoints to publish messages
│  │  ├─ model/        # Message DTOs
│  │  ├─ listener/     # Kafka consumer listeners
│  │  └─ producer/     # Kafka producers
│  └─ resources/
│     └─ application.yml
```

## Extending / Ideas

* Add schema validation or use Avro/Schema Registry
* Add retry/backoff and DLQ (dead-letter queue) configuration
* Add integration tests with Embedded Kafka
* Add security (OAuth2/JWT) to the REST endpoints

## Contributing

Contributions are welcome! Please open an issue first to discuss major changes. For small fixes, feel free to open a pull request.

## License

This project is released under the MIT License. See `LICENSE` for details.

---

*If you'd like, I can customize the README with your chosen project name, a sample `application.yml`, or add a CI badge (GitHub Actions).*

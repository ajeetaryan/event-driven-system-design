# Event-Driven Microservices Platform

This repository contains a Kafka-based Event-Driven Microservices architecture, designed with scalability, loose coupling, fault tolerance, and ownership boundaries in mind.

Each service follows clean producer/consumer design, supports retry + dead-letter topics, and is aligned with real-world system design best practices.

# Architecture Overview

The system follows an Event-Driven Architecture (EDA) using Apache Kafka as the central event backbone.

Key Principles

Loose coupling between services

Asynchronous communication

Clear ownership boundaries

Independent scaling

Failure isolation using Retry & DLT

Order Service  ─┐
Payment Service ├──▶ Kafka ▶── Inventory Service
Shipping Service┘              Notification Service

# Services Overview
# inventory-service

Role:
Consumes order and payment events to manage product inventory.

Responsibilities:

Listen to OrderCreated, OrderCancelled events

Reserve / release inventory

Idempotent event handling

Retry + Dead Letter Topic support

Pattern Used:

Kafka Consumer

Retryable Topics

DLT handling

 # notification-service

Role:
Sends notifications to users based on business events.

Responsibilities:

Consume domain events

Send email / SMS / push notifications

Non-blocking & async processing

Failure-safe via retries and DLT

Pattern Used:

Event-driven consumer

Fan-out messaging

Stateless processing

# order-service

Role:
Core business service responsible for order lifecycle.

Responsibilities:

Create orders

Publish OrderCreated events

Act as Kafka producer

Ensure reliable event publishing

Pattern Used:

Event Producer

Business-key partitioning

Immutable domain events

# payment-service

Role:
Handles payment processing and publishes payment status events.

Responsibilities:

Process payments

Publish PaymentCompleted / PaymentFailed events

Decouple payment logic from order flow

Pattern Used:

Event Producer

Transactional boundary

At-least-once delivery

# shipping-service

Role:
Manages shipping and delivery workflow.

Responsibilities:

Consume order & payment success events

Create shipment

Publish ShipmentCreated / ShipmentDelivered events

Pattern Used:

Event consumer + producer

Choreography-based workflow

 # Event Design Strategy

Events represent facts (past tense)

Immutable payloads

Business key–based partitioning

Versioned topics (e.g. order.lifecycle.v1)

Schema evolution ready

Example:

{
  "eventId": "uuid",
  "orderId": "ORD-123",
  "productId": "PROD-456",
  "quantity": 2,
  "occurredAt": "2026-01-16T10:30:00Z",
  "eventVersion": "v1"
}

# Reliability: Retry & Dead Letter Topics

All consumers support:

Retry topics with exponential backoff

Dead Letter Topics (DLT) for poison messages

Non-blocking failure handling

topic
 ├─ topic-retry-0
 ├─ topic-retry-1
 ├─ topic-retry-2
 └─ topic-dlt

# Tech Stack

Java 17

Spring Boot 3.x

Spring Kafka

Apache Kafka

PostgreSQL (where applicable)

Docker (optional)

Maven

# Local Setup
Start Kafka
docker-compose up -d

Run Services
mvn spring-boot:run

# Ownership Model
Component	Owned By
Source Services	Source Team
Kafka Pipeline	Platform / Pipeline Team
Consumers	Sink System Teams

This ensures clear responsibility boundaries and faster independent evolution.

# Why This Architecture?

Scales horizontally

Prevents tight coupling

Supports replay & reprocessing

Production-proven Kafka patterns

Interview & architect-level design

# Commit Message Convention
Initial commit for all changes


Used consistently across:

inventory-service

notification-service

order-service

payment-service

shipping-service

README.md

# Final Note

This repository demonstrates real-world, production-grade Event-Driven Microservices design, suitable for:

Large-scale systems

High-throughput platforms

Senior engineer / architect discussions

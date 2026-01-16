package com.example.order_service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

	private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

	public OrderEventPublisher(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishOrderCreated(OrderCreatedEvent event) {
		kafkaTemplate.send(KafkaTopics.ORDER_LIFECYCLE_V1, event.getOrderId(), // partition key
				event);
	}
}

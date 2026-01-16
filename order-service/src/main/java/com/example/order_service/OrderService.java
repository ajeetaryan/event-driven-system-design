// OrderService.java
package com.example.order_service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderEventPublisher publisher;

	public OrderService(OrderEventPublisher publisher) {
		this.publisher = publisher;
	}

	public String createOrder(String productId, int quantity) {

		String orderId = UUID.randomUUID().toString();

		var event = OrderEventFactory.createOrderCreated(orderId, productId, quantity);

		publisher.publishOrderCreated(event);

		return orderId;
	}
}

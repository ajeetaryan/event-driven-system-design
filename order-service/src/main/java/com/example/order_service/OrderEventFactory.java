package com.example.order_service;

import java.time.Instant;
import java.util.UUID;

public final class OrderEventFactory {

	private OrderEventFactory() {
	}

	public static OrderCreatedEvent createOrderCreated(String orderId, String productId, int quantity) {
		return new OrderCreatedEvent(UUID.randomUUID().toString(), orderId, productId, quantity, Instant.now());
	}
}

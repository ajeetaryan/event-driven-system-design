// OrderCreatedEvent.java
package com.example.inventory_service;

import java.time.Instant;

public class OrderCreatedEvent {

	private String eventId;
	private String orderId;
	private String productId;
	private int quantity;
	private Instant occurredAt;
	private String eventVersion = "v1";

	protected OrderCreatedEvent() {
	}

	public OrderCreatedEvent(String eventId, String orderId, String productId, int quantity, Instant occurredAt) {
		this.eventId = eventId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.occurredAt = occurredAt;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Instant getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(Instant occurredAt) {
		this.occurredAt = occurredAt;
	}

	public String getEventVersion() {
		return eventVersion;
	}

	public void setEventVersion(String eventVersion) {
		this.eventVersion = eventVersion;
	}

}

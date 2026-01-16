package com.example.notification_service;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class OrderLifecycleConsumer {

	private final NotificationService notificationService;

	public OrderLifecycleConsumer(NotificationService inventoryService) {
		this.notificationService = inventoryService;
	}

	@RetryableTopic(attempts = "4", // 1 original + 3 retries
			backoff = @org.springframework.retry.annotation.Backoff(delay = 2000, // 2 sec
					multiplier = 2.0 // exponential backoff
			), topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE, dltTopicSuffix = "-dlt")
	@KafkaListener(topics = KafkaTopics.ORDER_LIFECYCLE_V1, groupId = "notification-service")
	public void onOrderCreated(OrderCreatedEvent event) {

		if (ProcessedEventStore.isProcessed(event.getEventId())) {
			return; // idempotent
		}
		notificationService.sendNotificationEmail(event);

	}

	@DltHandler
	public void handleDlt(OrderCreatedEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.OFFSET) long offset, @Header(KafkaHeaders.EXCEPTION_MESSAGE) String error) {

		System.err.println("DLT MESSAGE ");
		System.err.println("Topic   : " + topic);
		System.err.println("Offset  : " + offset);
		System.err.println("Reason  : " + error);
		System.err.println("OrderId : " + event.getOrderId());
	}
}

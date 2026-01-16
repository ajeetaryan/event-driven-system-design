package com.example.notification_service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendNotificationEmail(OrderCreatedEvent event) {
		System.out.println("Email has been sent for order - " + event.getProductId());
	}

}

package com.example.notification_service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessedEventStore {

	//Redis or database
    private static final Set<String> processedEvents =
            ConcurrentHashMap.newKeySet();

    public static boolean isProcessed(String eventId) {
        return processedEvents.contains(eventId);
    }

    public static void markProcessed(String eventId) {
        processedEvents.add(eventId);
    }
}

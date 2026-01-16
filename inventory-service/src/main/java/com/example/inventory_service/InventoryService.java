package com.example.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public void reserveStock(String productId, int quantity) {
        // business logic
        System.out.println("Inventory reserved for product " + productId);
    }
}

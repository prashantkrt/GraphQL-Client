package com.mylearning.inventoryservice.dto;


public record ProductInput(String name, String category, Double price, Integer quantity, String description) {
}



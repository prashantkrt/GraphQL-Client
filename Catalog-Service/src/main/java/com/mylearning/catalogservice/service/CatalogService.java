package com.mylearning.catalogservice.service;

import com.mylearning.catalogservice.client.InventoryClient;
import com.mylearning.catalogservice.dto.ItemCategoryDto;
import com.mylearning.catalogservice.dto.ItemDto;
import com.mylearning.catalogservice.dto.ItemRequestDto;
import com.mylearning.catalogservice.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final InventoryClient inventoryClient;

    //query
    public List<ItemCategoryDto> getProductsByCategory(String category) {
        return inventoryClient.getProductsByCategory(category);
    }

    public List<ItemDto> getProducts() {
        return inventoryClient.getProducts();
    }

    //mutation
    public ItemResponseDto addProduct(ItemRequestDto itemRequestDto) {
        return inventoryClient.addProduct(itemRequestDto);
    }

    public ItemCategoryDto updateStock(Integer id, Integer quantity) {
        return inventoryClient.updateStock(id, quantity);
    }
}

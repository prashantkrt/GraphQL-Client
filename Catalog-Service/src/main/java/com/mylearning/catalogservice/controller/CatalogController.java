package com.mylearning.catalogservice.controller;

import com.mylearning.catalogservice.dto.ItemCategoryDto;
import com.mylearning.catalogservice.dto.ItemDto;
import com.mylearning.catalogservice.dto.ItemRequestDto;
import com.mylearning.catalogservice.dto.ItemResponseDto;
import com.mylearning.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public List<ItemDto> getProducts() {
        return catalogService.getProducts();
    }

    @GetMapping("/{category}")
    public List<ItemCategoryDto> getProductsByCategory(@PathVariable String category) {
        return catalogService.getProductsByCategory(category);
    }

    @PostMapping
    public ItemResponseDto addProduct(@RequestBody ItemRequestDto itemRequestDto) {
        return catalogService.addProduct(itemRequestDto);
    }

    @GetMapping("/{id}/{quantity}")
    public ItemCategoryDto updateStock(@PathVariable Integer id,@PathVariable Integer quantity) {
        return catalogService.updateStock(id, quantity);
    }

}

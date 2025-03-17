package com.mylearning.inventoryservice.controller;

import com.mylearning.inventoryservice.dto.ProductInput;
import com.mylearning.inventoryservice.entity.Product;
import com.mylearning.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductGraphQLController {

    private final ProductService productService;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Integer id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public ResponseEntity<List<Product>> getProductsByCategory(@Argument String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @QueryMapping
    public List<Product> getProductByPriceRange(@Argument Double minPrice, @Argument Double maxPrice) {
        return productService.getProductByPriceRange(minPrice, maxPrice);
    }

    //for create Update Delete use @MutationMapping
    @MutationMapping
    public Product updateStock(@Argument Integer id, @Argument Integer quantity) {
        return productService.updateStock(id, quantity);
    }

    @MutationMapping
    public Product receiveNewShipment(@Argument Integer id, @Argument Integer quantity) {
        return productService.receiveNewShipment(id, quantity);
    }

    // Mutation to add a product by passing a full object
    @MutationMapping
    public Product addProduct(@Argument("product") ProductInput productInput) {
        return productService.createProduct(productInput);
    }
}

package com.mylearning.inventoryservice.service;

import com.mylearning.inventoryservice.dto.ProductInput;
import com.mylearning.inventoryservice.entity.Product;
import com.mylearning.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //for restapi
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //for graphql
    public Product createProduct(ProductInput productInput) {
        Product product = new Product(productInput.name(), productInput.category(), productInput.price(), productInput.quantity(), productInput.description());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }


    public Product updateStock(Integer id, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product != null) {
            product.setQuantity(quantity);
            return productRepository.save(product);
        }
        return null;
    }


    public Product receiveNewShipment(Integer id, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            return productRepository.save(product);
        }
        return null;
    }

}

package com.mylearning.catalogservice.client;

import com.mylearning.catalogservice.dto.ItemCategoryDto;
import com.mylearning.catalogservice.dto.ItemDto;
import com.mylearning.catalogservice.dto.ItemRequestDto;
import com.mylearning.catalogservice.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryClient {

    private final HttpGraphQlClient httpGraphQlClient;

    // String query = "query GetAllProducts { getAllProducts { name price } }"; looks better and is more readable
    public List<ItemDto> getProducts() {
        String query = "query GetAllProducts {\n" +
                "    getAllProducts {\n" +
                "        name\n" +
                "        price\n" +
                "    }\n" +
                "}\n";

        Mono<List<ItemDto>> getAllProducts = httpGraphQlClient.document(query).retrieve("getAllProducts").toEntityList(ItemDto.class);
        return getAllProducts.block();
    }

    public List<ItemCategoryDto> getProductsByCategory(String category) {
        String query = String.format("query GetProductsByCategory {\n" +
                "    getProductsByCategory(category: \"%s\") {\n" +
                "        name\n" +
                "        category\n" +
                "        price\n" +
                "        quantity\n" +
                "        description\n" +
                "    }\n" +
                "}\n", category);

        Mono<List<ItemCategoryDto>> getAllProducts = httpGraphQlClient.document(query).retrieve("getProductsByCategory").toEntityList(ItemCategoryDto.class)
                .onErrorResume(error -> {
            log.error("Error fetching products by category: {}", category, error);
            return Mono.just(Collections.emptyList());
        });
        return getAllProducts.block();
    }

    public ItemCategoryDto updateStock(Integer id, Integer quantity) {
        String mutation = String.format("mutation ReceiveNewShipment {\n" +
                "    receiveNewShipment(id: \"%s\", quantity: %s) {\n" +
                "        name\n" +
                "        category\n" +
                "        price\n" +
                "        quantity\n" +
                "        description\n" +
                "    }\n" +
                "}\n", id, quantity);

        Mono<ItemCategoryDto> getAllProducts = httpGraphQlClient.document(mutation).retrieve("receiveNewShipment").toEntity(ItemCategoryDto.class);
        return getAllProducts.block();
    }

    public ItemResponseDto addProduct(ItemRequestDto itemRequestDto) {
        String mutation = String.format(
                "mutation { " +
                        "   addProduct(product: { " +
                        "       name: \"%s\", " +
                        "       category: \"%s\", " +
                        "       price: %.2f, " +  // Format price as floating point with 2 decimal places
                        "       quantity: %d, " +
                        "       description: \"%s\" " +
                        "   }) { " +
                        "       id " +
                        "       name " +
                        "       category " +
                        "       price " +
                        "       quantity " +
                        "       description " +
                        "   } " +
                        "}",
                itemRequestDto.getName(),
                itemRequestDto.getCategory(),
                itemRequestDto.getPrice(),
                itemRequestDto.getQuantity(),
                itemRequestDto.getDescription()
        );

        return httpGraphQlClient.document(mutation)
                .retrieve("addProduct")
                .toEntity(ItemResponseDto.class)
                .block();
    }

}

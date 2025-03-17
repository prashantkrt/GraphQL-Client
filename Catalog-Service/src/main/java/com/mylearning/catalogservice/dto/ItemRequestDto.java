package com.mylearning.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
    private String name;
    private String category;
    private Double price;
    private Integer quantity;
    private String description;
}

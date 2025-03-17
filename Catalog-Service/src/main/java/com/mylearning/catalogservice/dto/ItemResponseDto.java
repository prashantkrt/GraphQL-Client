package com.mylearning.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ItemResponseDto {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private Integer quantity;
    private String description;
}

package com.icecreamshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.icecreamshop.model.domain.ProductType;

@Data
public class ProductDTO {
    private Long id;

    private Long quantity;

    private float price;

    private String title;

    private String shortDesc;

    private String longDesc;

    private String ingredients;

    private String allergens;

    private String currency;

    private String photoUrl;

    private ProductType type;

    private double rating;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO category;
}

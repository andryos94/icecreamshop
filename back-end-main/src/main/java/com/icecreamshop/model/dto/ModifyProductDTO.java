package com.icecreamshop.model.dto;

import lombok.Data;
import com.icecreamshop.model.domain.ProductType;

@Data
public class ModifyProductDTO {

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

    private Long categoryId;
}

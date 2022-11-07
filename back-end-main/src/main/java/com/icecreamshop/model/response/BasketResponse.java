package com.icecreamshop.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketResponse {
    private List<BasketProductResponse> basketProducts;
}


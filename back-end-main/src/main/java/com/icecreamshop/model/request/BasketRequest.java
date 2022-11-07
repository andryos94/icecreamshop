package com.icecreamshop.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BasketRequest {
    @NotNull
    private List<BasketProductRequest> basketProducts;
}

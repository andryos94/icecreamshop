package com.icecreamshop.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BasketProductRequest {
    @NotNull
    @ApiModelProperty(example = "1")
    private Long productId;

    @NotNull
    @Min(value = 0)
    @ApiModelProperty(example = "2")
    private Long quantity;
}

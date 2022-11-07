package com.icecreamshop.model.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RatingDTO {

    private Long id;

    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int value;

    @NotNull
    private Long productId;

    @NotNull
    private String sessionId;
}

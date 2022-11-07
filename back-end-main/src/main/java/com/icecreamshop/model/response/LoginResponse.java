package com.icecreamshop.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LoginResponse {
    @ApiModelProperty(example = "header.payload.signature")
    private String jwt;
}

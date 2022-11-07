package com.icecreamshop.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterResponse {
    @ApiModelProperty(example = "jane.doe@mail.com")
    private String email;

    @ApiModelProperty(example = "jane.doe")
    private String username;
}

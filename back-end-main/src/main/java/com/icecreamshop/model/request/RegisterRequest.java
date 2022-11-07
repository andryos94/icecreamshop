package com.icecreamshop.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "password")
public class RegisterRequest {
    @Email
    @NotBlank
    @Length(max = 128)
    @ApiModelProperty(example = "jane.doe@mail.com")
    private String email;

    @NotBlank
    @Length(max = 64)
    @ApiModelProperty(example = "password")
    private String password;

    @NotBlank
    @Length(max = 16)
    @ApiModelProperty(example = "jane.doe")
    private String username;
}

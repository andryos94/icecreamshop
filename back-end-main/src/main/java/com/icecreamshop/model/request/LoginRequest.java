package com.icecreamshop.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "password")
public class LoginRequest {
    @Email
    @NotBlank
    @Length(max = 128)
    @ApiModelProperty(example = "jane.doe@mail.com")
    private String email;

    @NotBlank
    @Length(max = 64)
    @ApiModelProperty(example = "password")
    private String password;
}

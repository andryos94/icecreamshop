package com.icecreamshop.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class CreateCategoryRequest {
    @NotBlank
    @NotNull
    @Length(max = 50)
    @ApiModelProperty(example = "Name should be shorter than 50.")
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 200)
    @ApiModelProperty(example = "Description should be shorter than 200.")
    private String description;
}

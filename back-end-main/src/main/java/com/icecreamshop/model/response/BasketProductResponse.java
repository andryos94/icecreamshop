package com.icecreamshop.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketProductResponse {
    @ApiModelProperty(example = "1")
    private Long productId;

    @ApiModelProperty(example = "Pistachio Ice Cream")
    private String name;

    @ApiModelProperty(example = "2")
    private Long quantity;

    @ApiModelProperty(example = "10")
    private Long remainingQuantity;

    @ApiModelProperty(example = "3.5")
    private Float price;

    @ApiModelProperty(example = "7")
    private Float totalPrice;

    @ApiModelProperty(example = "$")
    private String currency;

    @ApiModelProperty(example = "https://cdn-sharing.adobecc.com/content/storage/id" +
            "/urn:aaid:sc:US:d1fb2711-6c15-4f90-943b-32d53a12e790;revision=0" +
            "?component_id=fb5a1149-00d3-4080-bc08-cba7044c730f&api_key=CometServer1" +
            "&access_token=1637876794_urn%3Aaaid%3Asc%3AUS%3Ad1fb2711-6c15-4f90-943b")
    private String photoUrl;
}

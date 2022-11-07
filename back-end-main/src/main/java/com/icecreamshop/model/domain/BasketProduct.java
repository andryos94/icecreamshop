package com.icecreamshop.model.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "basketProduct")
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    @Length(max = 50)
    private String name;

    @NotNull
    @Min(value = 0)
    private Long quantity;

    @NotNull
    @Min(value = 0)
    private Long remainingQuantity;

    @NotNull
    @Positive
    private Float price;

    @NotNull
    @Positive
    private Float totalPrice;

    @NotNull
    @Length(max = 3)
    private String currency;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;
}

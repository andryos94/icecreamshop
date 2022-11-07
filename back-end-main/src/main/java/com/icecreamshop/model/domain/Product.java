package com.icecreamshop.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Quantity should be positive.")
    private Long quantity;

    @NotNull
    @Positive(message = "Price should be positive.")
    private float price;

    @NotNull
    @Length(max = 50, message = "Title should be shorter than 50.")
    private String title;

    @Length(max = 200, message = "Short description should be shorter than 200.")
    @Column(name = "short_description")
    private String shortDesc;

    @Length(max = 1000, message = "Long description should be shorter than 1000.")
    @Column(name = "long_description")
    private String longDesc;

    @Length(max = 200, message = "Ingredients should be shorter than 200.")
    private String ingredients;

    @Length(max = 200, message = "Allergens should be shorter than 200.")
    private String allergens;

    @NotNull
    @Length(max = 3, message = "Currency should be shorter than 3.")
    private String currency;

    @Column(name = "photo_url")
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Rating> ratings;
}

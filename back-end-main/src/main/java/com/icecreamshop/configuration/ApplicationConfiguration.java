package com.icecreamshop.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.icecreamshop.model.domain.BasketProduct;
import com.icecreamshop.model.domain.Product;
import com.icecreamshop.model.domain.Rating;
import com.icecreamshop.model.dto.RatingDTO;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Rating.class, RatingDTO.class).addMappings(m -> m.map(src -> src.getProduct().getId(),
                RatingDTO::setProductId));
        Converter<Product, BasketProduct> productBasketProductConverter = context -> {
            Product product = context.getSource();
            return BasketProduct.builder()
                    .name(product.getTitle())
                    .remainingQuantity(product.getQuantity())
                    .price(product.getPrice())
                    .currency(product.getCurrency())
                    .photoUrl(product.getPhotoUrl())
                    .build();
        };
        modelMapper.addConverter(productBasketProductConverter, Product.class, BasketProduct.class);
        return modelMapper;
    }
}

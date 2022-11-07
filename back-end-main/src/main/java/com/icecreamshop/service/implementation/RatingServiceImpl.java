package com.icecreamshop.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.exception.UnauthorizedException;
import com.icecreamshop.model.domain.Product;
import com.icecreamshop.model.domain.Rating;
import com.icecreamshop.model.dto.AddRatingDTO;
import com.icecreamshop.model.dto.RatingDTO;
import com.icecreamshop.model.dto.UpdateRatingDTO;
import com.icecreamshop.repository.ProductRepository;
import com.icecreamshop.repository.RatingRepository;
import com.icecreamshop.service.interfaces.RatingService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public RatingDTO save(AddRatingDTO ratingDTO, String sessionId) throws NotFoundException {

        log.info("[RatingService] - save {}", ratingDTO);

        Optional<Product> existingProduct = productRepository.findById(ratingDTO.getProductId());
        if (existingProduct.isEmpty()) {

            log.error("[RatingService] - save: product with id = {} was not found", ratingDTO.getProductId());

            throw new NotFoundException(String.format("Product with id = %d was not found", ratingDTO.getProductId()));
        } else {
            Rating rating = modelMapper.map(ratingDTO, Rating.class);
            rating.setId(null);
            rating.setProduct(existingProduct.get());
            rating.setSessionId(sessionId);
            Rating newRating = ratingRepository.save(rating);

            log.info("[RatingService] - saved rating successful {}", newRating);

            return modelMapper.map(newRating, RatingDTO.class);
        }

    }

    @Override
    public List<RatingDTO> getRatingsForProduct(Long productId) throws NotFoundException {

        log.info("[RatingService] - get ratings for product with id = {}", productId);

        Optional<Product> existingProduct = productRepository.findById(productId);

        if (existingProduct.isEmpty()) {

            log.error("[RatingService] - get ratings for product: product with id = {} was not found", productId);

            throw new NotFoundException(String.format("Product with id = %d was not found", productId));
        } else {
            List<Rating> ratings = ratingRepository.findAllByProductId(productId);

            log.info("[RatingService] - get ratings for product with id = {} successful", productId);

            return ratings.stream().map(it -> modelMapper.map(it, RatingDTO.class)).collect(Collectors.toList());
        }


    }

    @Override
    public RatingDTO modifyRating(Long ratingId, UpdateRatingDTO ratingDTO, String sessionId)
            throws UnauthorizedException, NotFoundException {

        log.info("[RatingService] - update rating with id = {}", ratingId);

        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new NotFoundException(
                String.format("Rating with id = %d was not found", ratingId)));

        if (!Objects.equals(rating.getSessionId(), sessionId)) {
            log.error("[RatingService] - update rating {} unauthorized operation for session {}.", rating, sessionId);

            throw new UnauthorizedException(String.format("Unauthorized rating update for rating with id = %d!", ratingId));
        }

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(ratingDTO, rating);

        Rating modifiedRating = ratingRepository.save(rating);

        log.info("[RatingService] - saved rating {} successful", modifiedRating);

        return modelMapper.map(modifiedRating, RatingDTO.class);
    }
}

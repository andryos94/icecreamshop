package com.icecreamshop.service.interfaces;

import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.exception.UnauthorizedException;
import com.icecreamshop.model.dto.AddRatingDTO;
import com.icecreamshop.model.dto.RatingDTO;
import com.icecreamshop.model.dto.UpdateRatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO save(AddRatingDTO rating, String sessionId) throws NotFoundException;

    List<RatingDTO> getRatingsForProduct(Long productId) throws NotFoundException;

    RatingDTO modifyRating(Long ratingId, UpdateRatingDTO ratingDTO, String sessionId)
            throws UnauthorizedException, NotFoundException;

}

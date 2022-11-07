package com.icecreamshop.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.exception.UnauthorizedException;
import com.icecreamshop.model.dto.AddRatingDTO;
import com.icecreamshop.model.dto.RatingDTO;
import com.icecreamshop.model.dto.UpdateRatingDTO;
import com.icecreamshop.service.interfaces.RatingService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private RatingService ratingService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RatingDTO> saveRating(@Valid @RequestBody AddRatingDTO ratingDTO,
                                                @ApiIgnore HttpSession session) throws NotFoundException {

        log.info("[RatingController] - save rating {}", ratingDTO);

        return new ResponseEntity<>(ratingService.save(ratingDTO, session.getId()), HttpStatus.CREATED);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{productId}")
    public ResponseEntity<List<RatingDTO>> getProductRatings(@PathVariable Long productId) throws NotFoundException {

        log.info("[RatingController] - get product rating for product with id = {}.", productId);

        return ResponseEntity.ok(ratingService.getRatingsForProduct(productId));


    }

    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/{ratingId}")
    public ResponseEntity<RatingDTO> modifyRating(@PathVariable Long ratingId, @Valid @RequestBody UpdateRatingDTO ratingDTO,
                                                  @ApiIgnore HttpSession session) throws UnauthorizedException, NotFoundException {

        log.info("[RatingController] - update rating {} with id = {}.", ratingDTO, ratingId);

        return ResponseEntity.ok(ratingService.modifyRating(ratingId, ratingDTO, session.getId()));
    }


}

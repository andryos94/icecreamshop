package com.icecreamshop.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.request.BasketRequest;
import com.icecreamshop.model.response.BasketResponse;
import com.icecreamshop.service.interfaces.BasketService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.HttpURLConnection;

@RestController
@RequestMapping(value = "/basket")
@AllArgsConstructor
@Slf4j
public class BasketController {
    private BasketService basketService;

    @Operation(summary = "Get current session basket")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The basket was retrieved successfully.",
                    response = BasketResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The basket was not found.")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasketResponse> get(@ApiIgnore HttpSession session) throws NotFoundException {
        String sessionId = session.getId();
        log.info("[BasketController] get basket: {}", sessionId);
        return ResponseEntity.ok(basketService.get(sessionId));
    }

    @Operation(summary = "Create current session basket")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "The basket was created successfully.",
                    response = BasketResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "The request body is not valid."),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "A product was not found."),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT,
                    message = "The basket was already created or a product does not have enough quantity.")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasketResponse> create(@RequestBody @Valid BasketRequest basketRequest,
                                                 @ApiIgnore HttpSession session) throws ConflictException,
            NotFoundException {
        String sessionId = session.getId();
        log.info("[BasketController] create basket: {} - {}", sessionId, basketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.create(basketRequest, sessionId));
    }

    @Operation(summary = "Update current session basket")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The basket was updated successfully.",
                    response = BasketResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "The request body is not valid."),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The basket or a product was not found."),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "A product does not have enough quantity.")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasketResponse> update(@RequestBody @Valid BasketRequest basketRequest,
                                                 @ApiIgnore HttpSession session) throws NotFoundException,
            ConflictException {
        String sessionId = session.getId();
        log.info("[BasketController] update basket: {} - {}", sessionId, basketRequest);
        return ResponseEntity.ok(basketService.update(basketRequest, sessionId));
    }
}

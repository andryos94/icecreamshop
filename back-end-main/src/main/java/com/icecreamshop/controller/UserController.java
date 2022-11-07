package com.icecreamshop.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.IncorrectCredentialsException;
import com.icecreamshop.model.request.LoginRequest;
import com.icecreamshop.model.request.RegisterRequest;
import com.icecreamshop.model.response.LoginResponse;
import com.icecreamshop.model.response.RegisterResponse;
import com.icecreamshop.service.interfaces.UserService;
import com.icecreamshop.utility.JWTUtility;

import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.net.URI;

@RestController
@Validated
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JWTUtility jwtUtility;

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "The user was registered successfully.",
                    response = RegisterResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "The request body is not valid."),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "The email or username was already used.")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request)
            throws ConflictException {
        log.info("[UserController] register user: {}", request);
        RegisterResponse response = userService.register(request);
        log.info("[UserController] registered user: {}", response);
        return ResponseEntity.created(URI.create(String.format("users/%s", response.getUsername()))).body(response);
    }

    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The user was logged in successfully.",
                    response = LoginResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "The email is not valid."),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "The email or password is incorrect.")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) throws IncorrectCredentialsException {
        log.info("[UserController] login user: {}", request);
        userService.login(request);
        LoginResponse response = new LoginResponse(jwtUtility.generateJWT(userDetailsService
                .loadUserByUsername(request.getEmail())));
        log.info("[UserController] logged in user: {}", response);
        return ResponseEntity.ok(response);
    }
}

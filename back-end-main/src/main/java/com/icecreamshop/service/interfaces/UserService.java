package com.icecreamshop.service.interfaces;

import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.IncorrectCredentialsException;
import com.icecreamshop.model.request.LoginRequest;
import com.icecreamshop.model.request.RegisterRequest;
import com.icecreamshop.model.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request) throws ConflictException;

    void login(LoginRequest request) throws IncorrectCredentialsException;
}

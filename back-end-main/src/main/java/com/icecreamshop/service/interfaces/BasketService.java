package com.icecreamshop.service.interfaces;

import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.request.BasketRequest;
import com.icecreamshop.model.response.BasketResponse;

public interface BasketService {
    BasketResponse get(String sessionId) throws NotFoundException;

    BasketResponse create(BasketRequest basketRequest, String sessionId) throws ConflictException, NotFoundException;

    BasketResponse update(BasketRequest basketRequest, String sessionId) throws NotFoundException, ConflictException;
}

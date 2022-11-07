package com.icecreamshop.service.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.domain.Basket;
import com.icecreamshop.model.domain.BasketProduct;
import com.icecreamshop.model.domain.Product;
import com.icecreamshop.model.request.BasketProductRequest;
import com.icecreamshop.model.request.BasketRequest;
import com.icecreamshop.model.response.BasketResponse;
import com.icecreamshop.repository.BasketRepository;
import com.icecreamshop.repository.ProductRepository;
import com.icecreamshop.service.interfaces.BasketService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImplementation implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private Basket getBasket(String sessionId) throws NotFoundException {
        return basketRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new NotFoundException(String.format("The basket: '%s' was not found.", sessionId)));
    }

    private Product getProduct(Long productId) throws NotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("The product: '%s' was not found.", productId)));
    }

    private void checkQuantity(Long quantity, Long productId) throws ConflictException {
        if (quantity < 0) {
            throw new ConflictException(String.format("The product: '%s' does not have enough quantity.", productId));
        }
    }

    private void addBasketProduct(BasketProductRequest basketProductRequest, Product product, Basket basket,
                                  List<BasketProduct> basketProducts)
            throws ConflictException {
        long requestQuantity = basketProductRequest.getQuantity();
        if (requestQuantity == 0) {
            return;
        }
        long newQuantity = product.getQuantity() - requestQuantity;
        checkQuantity(newQuantity, product.getId());
        product.setQuantity(newQuantity);
        BasketProduct basketProduct = modelMapper.map(product, BasketProduct.class);
        modelMapper.map(basketProductRequest, basketProduct);
        basketProduct.setTotalPrice(requestQuantity * product.getPrice());
        basketProduct.setBasket(basket);
        basketProducts.add(basketProduct);
    }

    private void modifyBasketProduct(BasketProductRequest basketProductRequest, Product product,
                                     BasketProduct basketProduct, List<BasketProduct> basketProducts)
            throws ConflictException {
        long requestQuantity = basketProductRequest.getQuantity();
        if (requestQuantity == 0) {
            product.setQuantity(product.getQuantity() + basketProduct.getQuantity());
            basketProducts.remove(basketProduct);
            return;
        }
        long oldBasketProductQuantity = basketProduct.getQuantity();
        if (requestQuantity == oldBasketProductQuantity) {
            return;
        }
        long newProductQuantity = product.getQuantity() + oldBasketProductQuantity - requestQuantity;
        checkQuantity(newProductQuantity, product.getId());
        product.setQuantity(newProductQuantity);
        basketProduct.setQuantity(requestQuantity);
        basketProduct.setRemainingQuantity(newProductQuantity);
        basketProduct.setTotalPrice(requestQuantity * product.getPrice());
    }

    @Override
    public BasketResponse get(String sessionId) throws NotFoundException {
        return modelMapper.map(getBasket(sessionId), BasketResponse.class);
    }

    @Override
    public BasketResponse create(BasketRequest basketRequest, String sessionId) throws ConflictException,
            NotFoundException {
        if (basketRepository.existsBySessionId(sessionId)) {
            throw new ConflictException(String.format("The basket: '%s' was already created.", sessionId));
        }
        List<Product> products = new ArrayList<>();
        Basket newBasket = new Basket();
        newBasket.setSessionId(sessionId);
        List<BasketProduct> newBasketProducts = new ArrayList<>();
        for (BasketProductRequest basketProductRequest : basketRequest.getBasketProducts()) {
            Product product = getProduct(basketProductRequest.getProductId());
            products.add(product);
            addBasketProduct(basketProductRequest, product, newBasket, newBasketProducts);
        }
        productRepository.saveAll(products);
        newBasketProducts.forEach(basketProduct -> basketProduct.setBasket(newBasket));
        newBasket.setBasketProducts(newBasketProducts);
        Basket createdBasket = basketRepository.save(newBasket);
        return modelMapper.map(createdBasket, BasketResponse.class);
    }

    @Override
    public BasketResponse update(BasketRequest basketRequest, String sessionId) throws NotFoundException,
            ConflictException {
        List<Product> products = new ArrayList<>();
        Basket oldBasket = getBasket(sessionId);
        List<BasketProduct> oldBasketProducts = oldBasket.getBasketProducts();
        for (BasketProductRequest basketProductRequest : basketRequest.getBasketProducts()) {
            Long productId = basketProductRequest.getProductId();
            Product product = getProduct(productId);
            products.add(product);
            BasketProduct oldBasketProduct = oldBasketProducts.stream()
                    .filter(basketProduct -> basketProduct.getProductId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if (oldBasketProduct == null) {
                addBasketProduct(basketProductRequest, product, oldBasket, oldBasketProducts);
            } else {
                modifyBasketProduct(basketProductRequest, product, oldBasketProduct, oldBasketProducts);
            }
        }
        productRepository.saveAll(products);
        Basket updatedBasket = basketRepository.save(oldBasket);
        return modelMapper.map(updatedBasket, BasketResponse.class);
    }
}

package com.icecreamshop.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.domain.Category;
import com.icecreamshop.model.domain.Product;
import com.icecreamshop.model.domain.Rating;
import com.icecreamshop.model.dto.CategoryDTO;
import com.icecreamshop.model.dto.ModifyProductDTO;
import com.icecreamshop.model.dto.ProductDTO;
import com.icecreamshop.repository.CategoryRepository;
import com.icecreamshop.repository.ProductRepository;
import com.icecreamshop.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service

public class ProductServiceImpl implements ProductService {

    private ModelMapper modelMapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> getAllProducts() {

        log.info("[ProductService] - get all products request");

        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapProductToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllPaged(Integer page, Integer size) {
        log.info("[ProductService] - get all products request with page = {} and size = {}", page, size);

        Pageable paging = PageRequest.of(page, size);

        Page<Product> pagedProducts = productRepository.findAll(paging);
        if (pagedProducts.hasContent()) {

            List<Product> products = pagedProducts.getContent();

            return products.stream().map(this::mapProductToDTO).collect(Collectors.toList());

        } else
            return new ArrayList<>();

    }

    @Override
    public List<ProductDTO> getAllProductsByCategoryId(Long categoryId) throws NotFoundException {

        log.info("[ProductService] - get all products in category = {}", categoryId);

        checkCategoryId(categoryId);

        List<Product> products = productRepository.findAllByCategoryId(categoryId);

        return products.stream().map(this::mapProductToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllByCategoryIdPaged(Long categoryId, Integer page, Integer size) throws NotFoundException {

        log.info("[ProductService] - get all products in category = {} with page = {} and size = {}", categoryId, page, size);

        checkCategoryId(categoryId);

        Pageable paging = PageRequest.of(page, size);

        Page<Product> pagedProducts = productRepository.findAllByCategoryId(categoryId, paging);
        if (pagedProducts.hasContent()) {

            List<Product> products = pagedProducts.getContent();

            return products.stream().map(this::mapProductToDTO).collect(Collectors.toList());

        } else
            return new ArrayList<>();

    }

    @Override
    public ProductDTO getProductById(Long productId) throws NotFoundException {
        log.info("[ProductService] - getProduct with id = {} .", productId);

        Optional<Product> existingProduct = productRepository.findById(productId);

        if (existingProduct.isEmpty()) {
            log.error("[ProductService] - getProduct: product with id = {} was not found.", productId);
            throw new NotFoundException(String.format("Product with id = %d was not found!", productId));
        } else
            return mapProductToDTO(existingProduct.get());
    }

    private ProductDTO mapProductToDTO(Product product) {
        ProductDTO pDTO = modelMapper.map(product, ProductDTO.class);

        if (product.getCategory() != null)
            pDTO.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));

        // compute rating for each product
        if (product.getRatings() == null) {
            pDTO.setRating(0.0);
        } else {
            pDTO.setRating(product.getRatings().stream().mapToInt(Rating::getValue).average().orElse(0.0));
        }
        return pDTO;
    }

    private void checkCategoryId(Long categoryId) throws NotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(String.format("The category with id: '%d' was not found.", categoryId));
        }
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return mapProductToDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO modify(Long productId, ModifyProductDTO modifyProductDTO) throws NotFoundException {

        Product productDb = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %d doesn't exist", productId)));

        ProductDTO productDTO = modelMapper.map(modifyProductDTO, ProductDTO.class);

        if (modifyProductDTO.getCategoryId() != null) {
            Category cat = categoryRepository.findById(modifyProductDTO.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(
                            String.format("Category with id %d doesn't exist", modifyProductDTO.getCategoryId())));
            productDb.setCategory(cat);
        }

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(productDTO, productDb);

        productRepository.save(productDb);

        return mapProductToDTO(productDb);
    }
}

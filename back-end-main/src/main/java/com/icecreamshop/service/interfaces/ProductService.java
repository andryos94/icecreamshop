package com.icecreamshop.service.interfaces;


import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.dto.ModifyProductDTO;
import com.icecreamshop.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllPaged(Integer page, Integer size);

    List<ProductDTO> getAllProductsByCategoryId(Long categoryId) throws NotFoundException;

    List<ProductDTO> getAllByCategoryIdPaged(Long categoryId, Integer page, Integer size) throws NotFoundException;

    ProductDTO getProductById(Long productId) throws NotFoundException;

    ProductDTO create(ProductDTO productDTO);

    ProductDTO modify(Long productId, ModifyProductDTO productDTO) throws NotFoundException;
}

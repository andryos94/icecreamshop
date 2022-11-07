package com.icecreamshop.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.dto.ModifyProductDTO;
import com.icecreamshop.model.dto.ProductDTO;
import com.icecreamshop.service.interfaces.ProductService;

import javax.validation.constraints.Min;
import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/products")
@Validated
public class ProductController {

    private ProductService productService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) @Min(value = 0, message = "CategoryId must not be less than zero") Long categoryId,
                                                        @RequestParam(required = false) @Min(value = 0, message = "Page index must not be less than zero") Integer page,
                                                        @RequestParam(required = false) @Min(value = 1, message = "Size value must not be less than one") Integer size) throws NotFoundException {


        log.info("[ProductController] - getAllProducts");

        if (categoryId == null) {
            if (page != null && size != null)
                return new ResponseEntity<>(productService.getAllPaged(page, size), HttpStatus.OK);

            else
                return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } else {
            if (page != null && size != null)
                return new ResponseEntity<>(productService.getAllByCategoryIdPaged(categoryId, page, size), HttpStatus.OK);

            else
                return new ResponseEntity<>(productService.getAllProductsByCategoryId(categoryId), HttpStatus.OK);
        }

    }

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) throws NotFoundException {
        log.info("[ProductController] - getProduct with id = {}.", productId);

        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @PatchMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ProductDTO> modifyProduct(@PathVariable Long productId, @RequestBody ModifyProductDTO productDTO)
            throws NotFoundException {
        return ResponseEntity.ok(productService.modify(productId, productDTO));
    }
}

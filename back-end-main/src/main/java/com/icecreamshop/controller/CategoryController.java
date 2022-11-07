package com.icecreamshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.domain.Category;
import com.icecreamshop.model.dto.CategoryDTO;
import com.icecreamshop.model.request.CreateCategoryRequest;
import com.icecreamshop.service.interfaces.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class CategoryController {
    private CategoryService categoryService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CreateCategoryRequest request) {
        try {
            return ResponseEntity.ok(categoryService.addCategory(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/categories/{id}")
    public ResponseEntity<Category> modifyCategory(@PathVariable Long id, @RequestBody @Valid CreateCategoryRequest request)
            throws ConflictException {
        return ResponseEntity.ok(categoryService.modifyCategory(request, id));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") @Min(value = 1, message = "CategoryID must not be less than one") Long id)
            throws NotFoundException {
        if (categoryService.getCategoryById(id) != null) {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } else {
            throw new NotFoundException("Category not found!");
        }
    }
}

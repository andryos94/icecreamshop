package com.icecreamshop.service.interfaces;

import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.domain.Category;
import com.icecreamshop.model.dto.CategoryDTO;
import com.icecreamshop.model.request.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id) throws NotFoundException;

    Category addCategory(CreateCategoryRequest createCategory) throws ConflictException;

    Category modifyCategory(CreateCategoryRequest createCategory, Long id) throws ConflictException;
}

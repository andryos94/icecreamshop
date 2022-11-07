package com.icecreamshop.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.icecreamshop.exception.CategoryNotFoundException;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.model.domain.Category;
import com.icecreamshop.model.dto.CategoryDTO;
import com.icecreamshop.model.request.CreateCategoryRequest;
import com.icecreamshop.repository.CategoryRepository;
import com.icecreamshop.service.interfaces.CategoryService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    private void checkCategoryName(String name) throws ConflictException {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new ConflictException(String.format("The category name: '%s' was already used.", name));
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("[CategoryService] - get all categories");
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapCategoryToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(@Min(value = 1, message = "Category ID should not be lower then 0") Long id) throws NotFoundException {
        log.info("[CategoryService] - getCategory with id = {} .", id);

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new NotFoundException(String.format("Category with id = %d was not found!", id));
        } else
            return mapCategoryToDTO(category.get());
    }

    @Override
    public Category addCategory(CreateCategoryRequest request) throws ConflictException {
        log.info("[CategoryService] add category: {}", request);
        checkCategoryName(request.getName());
        Category category = modelMapper.map(request, Category.class);
        category = categoryRepository.save(category);
        Category response = modelMapper.map(category, Category.class);
        log.info("[CategoryService] created category: {}", response);
        return response;
    }

    @Override
    public Category modifyCategory(CreateCategoryRequest request, Long id) throws ConflictException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        log.info("[CategoryService] modify category: {}", request);
        checkCategoryName(request.getName());
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    private CategoryDTO mapCategoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}

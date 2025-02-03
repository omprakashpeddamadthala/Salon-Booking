package com.simplify.service.impl;

import com.simplify.dtos.CategoryDTO;
import com.simplify.dtos.SalonDTO;
import com.simplify.repository.CategoryRepository;
import com.simplify.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategoriesBySalonId(String salonId) {
        return List.of();
    }

    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }
}

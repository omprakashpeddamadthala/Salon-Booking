package com.simplify.service;

import com.simplify.dtos.CategoryDTO;
import com.simplify.dtos.SalonDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO);

    List<CategoryDTO> getAllCategoriesBySalonId(String salonId);

    CategoryDTO getCategoryById(UUID categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO,SalonDTO salonDTO,UUID categoryId);

    void deleteCategory(UUID categoryId);
}

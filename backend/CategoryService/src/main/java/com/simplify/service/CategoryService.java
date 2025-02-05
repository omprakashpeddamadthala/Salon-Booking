package com.simplify.service;

import com.simplify.dtos.CategoryDTO;
import com.simplify.dtos.SalonDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO);

    List<CategoryDTO> getAllCategoriesBySalonId(String salonId);

    CategoryDTO getCategoryById(String categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(String categoryId);
}

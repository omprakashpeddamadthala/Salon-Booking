package com.simplify.service.impl;

import com.simplify.dtos.CategoryDTO;
import com.simplify.dtos.SalonDTO;
import com.simplify.mapper.CategoryMapper;
import com.simplify.model.Category;
import com.simplify.repository.CategoryRepository;
import com.simplify.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO) {
        log.info( "Saving category with name: {}", categoryDTO.getName() );
        Category category =categoryMapper.mapToCategory( categoryDTO );
        category.setSalonId( salonDTO.getSalonId() );
        return categoryMapper.mapToCategoryDTO( categoryRepository.save( category ) );
    }

    @Override
    public List<CategoryDTO> getAllCategoriesBySalonId(String salonId) {
        log.info( "Fetching all categories by salonId: {}", salonId);
        List<Category> categories = categoryRepository.findAllBySalonId( salonId );
        log.info( "Fetched {} categories by salonId: {}", categories.size(), salonId);
        return categories.stream().map( categoryMapper::mapToCategoryDTO ).collect( Collectors.toList() );
    }

    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        log.info( "Fetching category by id: {}", categoryId );
        Category category = categoryRepository.findByCategoryId( categoryId );
        log.info( "Fetched category by id: {}", category.getCategoryId() );
        return categoryMapper.mapToCategoryDTO( category );
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }
}

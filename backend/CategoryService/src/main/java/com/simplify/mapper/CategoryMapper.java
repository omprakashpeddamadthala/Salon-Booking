package com.simplify.mapper;

import com.simplify.dtos.CategoryDTO;
import com.simplify.model.Category;
import com.simplify.payload.CategoryRequest;
import com.simplify.payload.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryDTO mapToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category mapToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryResponse mapToCategoryResponse(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryResponse.class);
    }

    public CategoryDTO mapCategoryRequestToCategoryDTO(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, CategoryDTO.class);
    }


    public CategoryDTO mapCategoryResponseToCategoryDTO(CategoryResponse categoryResponse) {
        return modelMapper.map(categoryResponse, CategoryDTO.class);
    }

}





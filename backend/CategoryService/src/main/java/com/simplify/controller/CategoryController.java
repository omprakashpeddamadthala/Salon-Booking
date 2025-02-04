package com.simplify.controller;

import com.simplify.dtos.CategoryDTO;
import com.simplify.dtos.SalonDTO;
import com.simplify.mapper.CategoryMapper;
import com.simplify.payload.CategoryRequest;
import com.simplify.payload.CategoryResponse;
import com.simplify.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(CategoryRequest categoryRequest) {
        log.info( "Received POST request to save category with name: {}", categoryRequest.getName() );
        SalonDTO salonDTO = new SalonDTO();
        CategoryDTO categoryDTO = categoryService.saveCategory( categoryMapper.mapCategoryRequestToCategoryDTO( categoryRequest ) ,salonDTO);
        log.info( "Successfully saved category with name: {}", categoryRequest.getName());
        return new ResponseEntity<>( categoryMapper.mapToCategoryResponse( categoryDTO ), HttpStatus.CREATED );
    }




}

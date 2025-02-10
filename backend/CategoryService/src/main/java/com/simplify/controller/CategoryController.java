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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        salonDTO.setSalonId( categoryRequest.getSalonId() );
        CategoryDTO categoryDTO = categoryService.saveCategory( categoryMapper.mapCategoryRequestToCategoryDTO( categoryRequest ) ,salonDTO);
        log.info( "Successfully saved category with name: {}", categoryRequest.getName());
        return new ResponseEntity<>( categoryMapper.mapToCategoryResponse( categoryDTO ), HttpStatus.CREATED );
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest categoryRequest, UUID categoryId) {
        log.info( "Received PUT request to update category with name: {}", categoryRequest.getName() );
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setSalonId( categoryRequest.getSalonId() );
        CategoryDTO categoryDTO = categoryService.updateCategory( categoryMapper.mapCategoryRequestToCategoryDTO( categoryRequest),salonDTO,categoryId );
        log.info( "Successfully updated category with name: {}", categoryRequest.getName());
        return new ResponseEntity<>( categoryMapper.mapToCategoryResponse( categoryDTO ), HttpStatus.OK );
    }

    @DeleteMapping({"/{categoryId}"})
    public ResponseEntity<String> deleteCategory(UUID categoryId) {
        log.info( "Received DELETE request to delete category with categoryId: {}", categoryId );
        categoryService.deleteCategory( categoryId );
        log.debug( "Deleted category with categoryId: {}", categoryId );
        return new ResponseEntity<>( "Deleted category with categoryId: " + categoryId,HttpStatus.OK );
    }

    @GetMapping({"/{categoryId}"})
    public ResponseEntity<CategoryResponse> getCategoryById(UUID categoryId) {
        log.info( "Received GET request to retrieve category with categoryId: {}", categoryId );
        CategoryDTO categoryDTO = categoryService.getCategoryById( categoryId );
        log.debug( "Retrieved category with categoryId: {}", categoryId );
        return new ResponseEntity<>( categoryMapper.mapToCategoryResponse( categoryDTO ), HttpStatus.OK );
    }

    @GetMapping({"salon/{salonId}"})
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesBySalonId(String salonId) {
        log.info( "Received GET request to retrieve all categories by salonId: {}", salonId );
        List<CategoryDTO> categories = categoryService.getAllCategoriesBySalonId( salonId );
        log.debug( "Retrieved {} categories by salonId: {}", categories.size(), salonId );
        return new ResponseEntity<>( categories.stream().map( categoryMapper::mapToCategoryResponse ).collect( Collectors.toList() ), HttpStatus.OK );
    }


}

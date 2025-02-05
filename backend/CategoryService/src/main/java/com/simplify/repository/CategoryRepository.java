package com.simplify.repository;

import com.simplify.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Serializable> {

    List<Category> findAllBySalonId(String salonId);

    Category findByCategoryId(String categoryId);
}

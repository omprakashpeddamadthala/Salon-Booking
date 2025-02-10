package com.simplify.repository;

import com.simplify.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findAllBySalonId(String salonId);

    Category findByCategoryId(UUID categoryId);

    void deleteByCategoryId(UUID categoryId);
}

package com.simplify.repository;

import com.simplify.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CategoryRepository extends JpaRepository<Category, Serializable> {
}

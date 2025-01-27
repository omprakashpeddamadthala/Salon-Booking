package com.simplify.repository;

import com.simplify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<User, Serializable> {
}

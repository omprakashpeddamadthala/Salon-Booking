package com.simplify.repository;

import com.simplify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Serializable> {

    Optional<User> findByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}

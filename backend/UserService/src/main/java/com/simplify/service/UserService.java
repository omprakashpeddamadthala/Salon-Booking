package com.simplify.service;

import com.simplify.dtos.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(Long id, UserDTO user);
    void deleteUser(Long id);
}

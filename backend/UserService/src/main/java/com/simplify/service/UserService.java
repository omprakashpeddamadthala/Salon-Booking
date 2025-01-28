package com.simplify.service;

import com.simplify.dtos.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserByUserId(String userId);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(String userId, UserDTO user);
    void deleteUserByUserId(String userId);
}

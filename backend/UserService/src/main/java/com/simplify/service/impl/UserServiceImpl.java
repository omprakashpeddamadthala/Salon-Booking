package com.simplify.service.impl;

import com.simplify.dtos.UserDTO;
import com.simplify.exception.EmailAlreadyExistsException;
import com.simplify.exception.UserNotFoundException;
import com.simplify.mapper.UserMapper;
import com.simplify.model.User;
import com.simplify.repository.UserRepository;
import com.simplify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users from database");
        List<User> users = userRepository.findAll();
        log.debug("Found {} users in database", users.size());
        return users.stream().map(userMapper::mapToUserDTO)
                .collect( Collectors.toList() );
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        log.debug("Fetching user with userId: {}", userId);
        User user = this.getUser( userId );
        log.info("Found user with userId: {}", userId);
        return userMapper.mapToUserDTO(user);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating new user with email: {}", userDTO.getEmail());
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            log.warn("User with username {} already exists", userDTO.getUsername());
            throw new RuntimeException(userDTO.getUsername());
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            log.warn("User with email {} already exists", userDTO.getEmail());
            throw new EmailAlreadyExistsException(userDTO.getEmail());
        }

        User user = userMapper.mapToUser( userDTO );
        user.setUserId( UUID.randomUUID().toString() );
        User savedUser = userRepository.save( user );
        log.debug("Created user {} with id: {}", userDTO.getEmail(), savedUser.getId());
        return userMapper.mapToUserDTO( savedUser );
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO user) {
        try {
            User existingUser = this.getUser( userId );
            log.info("Updating user {} with userId: {}", user.getEmail(), userId);
            existingUser =userMapper.mapToUser( user );
            User updatedUser = userRepository.save(existingUser);
            log.info("Updated user {} with userId: {}", user.getEmail(), updatedUser.getId());
            return userMapper.mapToUserDTO(updatedUser);
        } catch (Exception e) {
            log.error("Error updating user with userId {}: {}", userId, e.getMessage(), e);
            throw new RuntimeException("Failed to update user", e);
        }
    }


    @Override
    public void deleteUserByUserId(String userId) {
        log.info( "Deleting user with id: {}", userId );
        User user = this.getUser( userId );
        userRepository.delete( user );
        log.info( "Deleted user with id: {}", userId );
    }

    private User getUser(String userId) {
        return userRepository.findByUserId( userId )
                .orElseThrow( () -> {
                    log.error( "User not found with id: {}", userId );
                    return new UserNotFoundException( "User not found with id: " + userId );
                } );
    }
}

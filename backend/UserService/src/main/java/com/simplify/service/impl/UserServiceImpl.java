package com.simplify.service.impl;

import com.simplify.dtos.UserDTO;
import com.simplify.mapper.UserMapper;
import com.simplify.model.User;
import com.simplify.repository.UserRepository;
import com.simplify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserDTO getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        User user = getUser( id );
        log.info("Found user with id: {}", id);
        return userMapper.mapToUserDTO(user);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating new user with email: {}", userDTO.getEmail());
        User user = userMapper.mapToUser( userDTO );
        User savedUser = userRepository.save( user );
        log.debug("Created user {} with id: {}", userDTO.getEmail(), savedUser.getId());
        return userMapper.mapToUserDTO( savedUser );
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        try {
            User existingUser = this.getUser( id );
            log.info("Updating user {} with id: {}", user.getEmail(), id);
            existingUser =userMapper.mapToUser( user );
            User updatedUser = userRepository.save(existingUser);
            log.info("Updated user {} with id: {}", user.getEmail(), updatedUser.getId());
            return userMapper.mapToUserDTO(updatedUser);
        } catch (Exception e) {
            log.error("Error updating user with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update user", e);
        }
    }


    @Override
    public void deleteUser(Long id) {
        log.info( "Deleting user with id: {}", id );
        User user = getUser( id );
        userRepository.delete( user );
        log.info( "Deleted user with id: {}", id );
    }

    private User getUser(Long id) {
        return userRepository.findById( id )
                .orElseThrow( () -> {
                    log.error( "User not found with id: {}", id );
                    return new RuntimeException( "User not found with id: " + id );
                } );
    }
}

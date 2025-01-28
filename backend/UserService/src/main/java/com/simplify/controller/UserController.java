package com.simplify.controller;

import com.simplify.dtos.UserDTO;
import com.simplify.mapper.UserMapper;
import com.simplify.payload.UserRequest;
import com.simplify.payload.UserResponse;
import com.simplify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping( "/api/v1/users" )
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info( "Received GET request to retrieve all users" );
        List<UserDTO> userDTOS = userService.getAllUsers();
        log.debug( "Retrieved {} users from service layer", userDTOS.size() );
        List<UserResponse> userResponses = userDTOS.stream().map( userMapper::mapToUserResponse ).collect( Collectors.toList() );
        log.info( "Returning {} users in response", userResponses.size() );
        return new ResponseEntity<>( userResponses, HttpStatus.OK );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
        log.info( "Received GET request to retrieve user with userId: {}", userId );
        UserDTO userDTO = userService.getUserByUserId( userId );
        UserResponse userResponse = userMapper.mapToUserResponse( userDTO );
        log.debug( "Retrieved user {} with userId: {}", userResponse.getEmail(), userId );
        return new ResponseEntity<>( userResponse, HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid  @RequestBody UserRequest userRequest) {
        log.info( "Received POST request to create new user with email: {}", userRequest.getEmail() );
        UserDTO userDTO = userService.createUser( userMapper.mapToUserDTO( userRequest ) );
        UserResponse userResponse = userMapper.mapToUserResponse( userDTO );
        log.debug( "Created user {} with email: {}", userRequest.getEmail(), userResponse.getEmail() );
        return new ResponseEntity<>( userResponse, HttpStatus.CREATED );
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@Valid  @RequestBody UserRequest userRequest, @PathVariable String userId) {
        log.debug( "Received PUT request to update user with userId: {}", userId );
        try {
            UserDTO userDTO = userService.updateUser( userId, userMapper.mapToUserDTO( userRequest ) );
            UserResponse userResponse = userMapper.mapToUserResponse( userDTO );
            log.info( "Successfully updated user {} with new data", userRequest.getEmail() );
            return new ResponseEntity<>( userResponse, HttpStatus.OK );
        } catch (Exception e) {
            log.error( "Error updating user with userId {}: {}", userId, e.getMessage(), e );
            throw new RuntimeException( "Failed to update user", e );
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String  userId) {
        log.info( "Received DELETE request to delete user with id: {}", userId );
        try {
            userService.deleteUserByUserId( userId );
            log.info( "Successfully deleted user with userId: {}", userId );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            log.error( "Error deleting user with userId {}: {}", userId, e.getMessage(), e );
            throw new RuntimeException( "Failed to delete user", e );
        }
    }


}


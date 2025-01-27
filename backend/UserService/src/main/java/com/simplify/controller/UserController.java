package com.simplify.controller;

import com.simplify.dtos.UserDTO;
import com.simplify.mapper.UserMapper;
import com.simplify.model.User;
import com.simplify.payload.UserRequest;
import com.simplify.payload.UserResponse;
import com.simplify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        log.info( "Received POST request to create new user with email: {}", userRequest.getEmail() );
        UserDTO userDTO = userService.createUser( userMapper.mapToUserDTO( userRequest ) );
        UserResponse userResponse = userMapper.mapToUserResponse( userDTO );
        log.debug( "Created user {} with email: {}", userRequest.getEmail(), userResponse.getEmail() );
        return new ResponseEntity<>( userResponse, HttpStatus.CREATED );
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        log.debug( "Received PUT request to update user with id: {}", id );
        try {
            UserDTO userDTO = userService.updateUser( id, userMapper.mapToUserDTO( userRequest ) );
            UserResponse userResponse = userMapper.mapToUserResponse( userDTO );
            log.info( "Successfully updated user {} with new data", userRequest.getEmail() );
            return new ResponseEntity<>( userResponse, HttpStatus.OK );
        } catch (Exception e) {
            log.error( "Error updating user with id {}: {}", id, e.getMessage(), e );
            throw new RuntimeException( "Failed to update user", e );
        }
    }


}


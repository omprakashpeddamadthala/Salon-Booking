package com.simplify.mapper;

import com.simplify.dtos.UserDTO;
import com.simplify.model.User;
import com.simplify.payload.UserRequest;
import com.simplify.payload.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserResponse mapToUserResponse(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserResponse.class);
    }


    public UserDTO mapToUserDTO(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserDTO.class);
    }




}

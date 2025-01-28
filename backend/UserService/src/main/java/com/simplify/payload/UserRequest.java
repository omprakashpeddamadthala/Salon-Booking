package com.simplify.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    @NotBlank(message = "username is mandatory")
    private String username;
    private String lastName;
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    @NotBlank(message = "role is mandatory")
    private String role;
}

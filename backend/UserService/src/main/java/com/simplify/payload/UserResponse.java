package com.simplify.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    private String role;
}

package com.simplify.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalonRequest {
    @NotBlank(message = "name is mandatory")
    private String name;
    private List<String> images;
    private String address;
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    @NotBlank(message = "ownerId is mandatory")
    private Long ownerId;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}

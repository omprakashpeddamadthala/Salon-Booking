package com.simplify.payload;

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
public class SalonResponse {
    private String salonId;
    private String name;
    private List<String> images;
    private String address;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    private Long ownerId;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}

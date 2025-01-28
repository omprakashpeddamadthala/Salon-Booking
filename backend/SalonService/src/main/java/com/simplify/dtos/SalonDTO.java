package com.simplify.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalonDTO {
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
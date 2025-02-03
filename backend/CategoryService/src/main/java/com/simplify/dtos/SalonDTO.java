package com.simplify.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalonDTO {

    private String salonId;
    private String name;
    private String address;
    private String description;
    private String image;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    private Long ownerId;
}

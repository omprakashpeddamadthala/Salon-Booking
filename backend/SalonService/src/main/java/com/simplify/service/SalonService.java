package com.simplify.service;

import com.simplify.dtos.SalonDTO;
import com.simplify.dtos.UserDTO;

import java.util.List;

public interface SalonService {

    SalonDTO createSalon(SalonDTO salonDTO, UserDTO userDTO);

    SalonDTO updateSalon(SalonDTO salonDTO,UserDTO userDTO);

    List<SalonDTO> getAllSalons();

    SalonDTO getSalonBySalonId(String salonId);

    void deleteSalonBySalonId(String salonId);

    SalonDTO getSalonsByOwnerId(Long ownerId);

    SalonDTO searchSalonByCity(String city);

}

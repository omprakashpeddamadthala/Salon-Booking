package com.simplify.service;

import com.simplify.dtos.SalonDTO;
import com.simplify.dtos.UserDTO;

import java.util.List;

public interface SalonService {

    SalonDTO createSalon(SalonDTO salonDTO, UserDTO userDTO);

    SalonDTO updateSalon(SalonDTO salonDTO,UserDTO userDTO, String salonId);

    List<SalonDTO> getAllSalons();

    SalonDTO getSalonBySalonId(String salonId);

    void deleteSalonBySalonId(String salonId);

    List<SalonDTO> searchSalonByCity(String city);

}

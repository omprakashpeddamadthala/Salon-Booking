package com.simplify.service.impl;

import com.simplify.dtos.SalonDTO;
import com.simplify.dtos.UserDTO;
import com.simplify.mapper.SalonMapper;
import com.simplify.model.Salon;
import com.simplify.repository.SalonRepository;
import com.simplify.service.SalonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    @Override
    public SalonDTO createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        log.info( "Creating a new salon with salonDTO: {}", salonDTO.getName()  );
        //todo making dynamic
        UserDTO user = UserDTO.builder().id( 1L ).fullName( "John Doe" ).email( "john@example.com" ).build();
        Salon salon= salonMapper.mapToSalon( salonDTO );
        salon.setOwnerId( user.getId() );
        Salon savedSalon =salonRepository.save( salon );
        return salonMapper.mapToSalonDTO( savedSalon );
    }

    @Override
    public SalonDTO updateSalon(SalonDTO salonDTO, UserDTO userDTO) {
        log.info( "" );
        return null;
    }

    @Override
    public List<SalonDTO> getAllSalons() {
        log.info( "" );
        return List.of();
    }

    @Override
    public SalonDTO getSalonBySalonId(String salonId) {
        log.info( "" );
        return null;
    }

    @Override
    public void deleteSalonBySalonId(String salonId) {
        log.info( "" );

    }

    @Override
    public SalonDTO getSalonsByOwnerId(Long ownerId) {
        log.info( "" );
        return null;
    }

    @Override
    public SalonDTO searchSalonByCity(String city) {
        log.info( "" );
        return null;
    }
}

package com.simplify.service.impl;

import com.simplify.dtos.SalonDTO;
import com.simplify.dtos.UserDTO;
import com.simplify.mapper.SalonMapper;
import com.simplify.model.Salon;
import com.simplify.repository.SalonRepository;
import com.simplify.service.SalonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    @Override
    public SalonDTO createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        log.info( "Creating a new salon with salonDTO: {}", salonDTO.getName() );
        Salon salon = salonMapper.mapToSalon( salonDTO );
        salon.setSalonId( UUID.randomUUID().toString() );
        salon.setOwnerId( userDTO.getId() );
        Salon savedSalon = salonRepository.save( salon );
        return salonMapper.mapToSalonDTO( savedSalon );
    }

    @Override
    public SalonDTO updateSalon(SalonDTO salonDTO, UserDTO userDTO, String salonId) {
        log.info( "Updating salon with salonDTO: {}", salonDTO.getName() );
        Salon existingSalon  = this.getSalonDetails( salonId );
        existingSalon.setName( salonDTO.getName() );
        existingSalon.setAddress( salonDTO.getAddress() );
        existingSalon.setCity( salonDTO.getCity() );
        existingSalon.setState( salonDTO.getState() );
        existingSalon.setZip( salonDTO.getZip() );
        existingSalon.setCountry( salonDTO.getCountry() );
        existingSalon.setPhoneNumber( salonDTO.getPhoneNumber() );
        existingSalon.setOwnerId( userDTO.getId() );
        existingSalon.setSalonId( salonId );
        Salon savedSalon = salonRepository.save( existingSalon );
        return salonMapper.mapToSalonDTO( savedSalon );
    }

    @Override
    public List<SalonDTO> getAllSalons() {
        log.info( "Getting all salons FROM database" );
        List<Salon> salonList = salonRepository.findAll();
        log.debug( "Found {} salons in database", salonList.size() );
        if (!salonList.isEmpty()) {
            log.info( "Returning {} salon in response", salonList.size() );
            return salonList.stream().map( salonMapper::mapToSalonDTO ).collect( Collectors.toList() );
        } else {
            log.warn( "No salon found in database" );
        }
        return List.of();
    }

    @Override
    public SalonDTO getSalonBySalonId(String salonId) {
        log.info( "Fetch salon with salonId: {}", salonId );
        Salon salon = this.getSalonDetails( salonId );
        log.info( "Founded salon with salonId: {}", salon.getSalonId() );
        return salonMapper.mapToSalonDTO( salon );
    }

    @Override
    public void deleteSalonBySalonId(String salonId) {
        log.info( "Deleting salon with salonId: {}", salonId );
        salonRepository.deleteBySalonId( salonId );
        log.info( "Deleted salon with salonId: {}", salonId );
    }

    @Override
    public List<SalonDTO> searchSalonByCity(String city) {
        log.info( "Searching salon by city: {}", city );
        List<Salon> salon =salonRepository.searchSalonByCity( city );
        if (!salon.isEmpty()) {
            log.info( "Found {} salon in database", salon.size() );
            return salon.stream().map( salonMapper::mapToSalonDTO ).collect( Collectors.toList() );
        }
        return List.of();
    }

    private Salon getSalonDetails(String salonId) {
        return salonRepository.findBySalonId( salonId ).orElseThrow( () -> new RuntimeException( "Salon details not found" ) );
    }
}

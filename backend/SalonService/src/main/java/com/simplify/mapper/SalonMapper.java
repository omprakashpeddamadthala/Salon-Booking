package com.simplify.mapper;

import com.simplify.dtos.SalonDTO;
import com.simplify.model.Salon;
import com.simplify.payload.SalonRequest;
import com.simplify.payload.SalonResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalonMapper {

    private final ModelMapper modelMapper;

    public SalonDTO mapToSalonDTO(Salon salon) {
        return modelMapper.map(salon, SalonDTO.class);
    }

    public Salon mapToSalon(SalonDTO salonDTO) {
        return modelMapper.map(salonDTO, Salon.class);
    }

    public SalonResponse mapToSalonResponse(SalonDTO salonDTO) {
        return modelMapper.map(salonDTO, SalonResponse.class);
    }

    public SalonDTO mapToSalonDTO(SalonRequest salonRequest) {
        return modelMapper.map( salonRequest, SalonDTO.class );
    }


}

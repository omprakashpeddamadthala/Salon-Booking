package com.simplify.config;

import com.simplify.dtos.SalonDTO;
import com.simplify.model.Salon;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy( MatchingStrategies.STRICT);

        // Explicit mapping for Salon
        modelMapper.typeMap( SalonDTO.class, Salon.class)
                .addMappings(mapper -> {
                    mapper.map(SalonDTO::getSalonId, Salon::setId);
                    mapper.map(SalonDTO::getOwnerId, Salon::setOwnerId);
                    // Add other explicit mappings as needed
                });

        return modelMapper;
    }
}

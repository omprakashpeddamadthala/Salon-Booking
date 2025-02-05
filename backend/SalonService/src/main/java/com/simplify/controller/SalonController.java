package com.simplify.controller;

import com.simplify.dtos.SalonDTO;
import com.simplify.dtos.UserDTO;
import com.simplify.mapper.SalonMapper;
import com.simplify.payload.SalonRequest;
import com.simplify.payload.SalonResponse;
import com.simplify.service.SalonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/salons")
public class SalonController {

    private final SalonService salonService;
    private final SalonMapper salonMapper;

    @PostMapping
    public ResponseEntity<SalonResponse> createSalon(@Valid @RequestBody SalonRequest salonRequest) {
        log.info( "Received POST request to create salon with name: {}", salonRequest.getName() );
        SalonDTO salonDTO = salonMapper.salonRequestToSalonDTO( salonRequest );
        //todo making dynamic userDto
        UserDTO userDto = UserDTO.builder().id( 1L ).fullName( "John Doe" ).email( "john@example.com" ).build();
        SalonResponse salonResponse =salonMapper.mapToSalonResponse( salonService.createSalon( salonDTO ,userDto ));
        log.debug( "Created salon {} with salonId: {}", salonRequest.getName(), salonResponse.getSalonId() );
        return new ResponseEntity<>( salonResponse,HttpStatus.CREATED );
    }

    @PutMapping("/{salonId}")
    public ResponseEntity<SalonResponse> updateSalon(@Valid @RequestBody SalonRequest salonRequest, @PathVariable String salonId) {
        log.info( "Received POST request to update salon with name: {}", salonRequest.getName() );
        SalonDTO salonDTO = salonMapper.salonRequestToSalonDTO( salonRequest );
        //todo making dynamic userDto
        UserDTO userDto = UserDTO.builder().id( 1L ).fullName( "John Doe" ).email( "john@example.com" ).build();
        SalonResponse salonResponse =salonMapper.mapToSalonResponse( salonService.updateSalon( salonDTO ,userDto ,salonId ));
        log.debug( "Updated salon {} with salonId: {}", salonRequest.getName(), salonResponse.getSalonId() );
        return  new ResponseEntity<>( salonResponse,HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<SalonResponse>> getAllSalons() {
        log.info( "Received GET request to retrieve all salons" );
        List<SalonDTO> salonDTOS = salonService.getAllSalons();
        log.debug( "Retrieved {} salons from service layer", salonDTOS.size() );
        List<SalonResponse> salonResponses = salonDTOS.stream().map( salonMapper::mapToSalonResponse ).collect( Collectors.toList() );
        log.info( "Returning {} salons in response", salonResponses.size() );
        return new ResponseEntity<>( salonResponses,HttpStatus.OK );
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonResponse> getSalonBySalonId( @PathVariable String salonId) {
        log.info( "Received GET request to retrieve salon with salonId: {}", salonId );
        SalonDTO salonDTO = salonService.getSalonBySalonId( salonId );
        SalonResponse salonResponse = salonMapper.mapToSalonResponse( salonDTO );
        log.debug( "Retrieved salon {} with salonId: {}", salonResponse.getName(), salonId );
        return new ResponseEntity<>( salonResponse,HttpStatus.OK );
    }

    @DeleteMapping("/{salonId}")
    public ResponseEntity<String> deleteSalonBySalonId(@PathVariable String salonId) {
        log.info( "Received DELETE request to delete salon with salonId: {}", salonId );
        salonService.deleteSalonBySalonId( salonId );
        log.debug( "Deleted salon with salonId: {}", salonId );
        return new ResponseEntity<>( "Deleted salon with salonId: " + salonId,HttpStatus.OK );
    }


    @GetMapping("/search}")
    public ResponseEntity<List<SalonResponse>> searchSalonByCity(@RequestParam String city) {
        log.info( "Received GET request to search salon by city: {}", city );
        List<SalonDTO> salonDTOS = salonService.searchSalonByCity( city );
        List<SalonResponse> salonResponses = salonDTOS.stream().map( salonMapper::mapToSalonResponse ).collect( Collectors.toList() );
        return new ResponseEntity<>( salonResponses,HttpStatus.OK );
    }
}

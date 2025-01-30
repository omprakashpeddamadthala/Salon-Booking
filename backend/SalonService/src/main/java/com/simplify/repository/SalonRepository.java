package com.simplify.repository;

import com.simplify.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalonRepository extends JpaRepository<Salon, Long> {
    Optional<Salon> findBySalonId(String salonId);

    void deleteBySalonId(String salonId);

    Optional<Salon> findByOwnerId(Long ownerId);

    @Query("SELECT s FROM Salon s WHERE s.city = :city")
    List<Salon> searchSalonByCity(String city);
}

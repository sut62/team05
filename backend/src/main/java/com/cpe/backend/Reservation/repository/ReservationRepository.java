package com.cpe.backend.Reservation.repository;

import com.cpe.backend.Reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public
interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(long id);
}
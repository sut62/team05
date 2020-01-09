package com.cpe.backend.Reservation.repository;
import java.util.Collection;
import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Members.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findById(long id);
}
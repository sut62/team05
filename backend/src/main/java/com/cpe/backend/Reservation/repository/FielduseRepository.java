package com.cpe.backend.Reservation.repository;

import java.util.Collection;

import com.cpe.backend.Reservation.entity.Fielduse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FielduseRepository extends JpaRepository<Fielduse, Long> {
    Fielduse findById(long id);
}
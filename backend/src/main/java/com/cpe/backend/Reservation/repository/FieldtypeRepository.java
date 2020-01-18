package com.cpe.backend.Reservation.repository;

import java.util.Collection;

import com.cpe.backend.Reservation.entity.Fieldtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FieldtypeRepository extends JpaRepository<Fieldtype, Long> {
    Fieldtype findById(long id);
}
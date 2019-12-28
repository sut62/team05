package com.cpe.backend.Sportequipment.repository;

import com.cpe.backend.Sportequipment.entity.Sportequipment;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
public interface SportequipmentRepository extends JpaRepository<Sportequipment, Long> {
    Sportequipment findById(long id);
}
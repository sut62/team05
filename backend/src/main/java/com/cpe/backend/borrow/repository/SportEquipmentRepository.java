package com.cpe.backend.borrow.repository;

import com.cpe.backend.borrow.entity.SportEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SportEquipmentRepository extends JpaRepository<SportEquipment, Long> {
    SportEquipment findById(long id);
}

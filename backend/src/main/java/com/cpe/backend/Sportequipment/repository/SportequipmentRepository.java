package com.cpe.backend.Sportequipment.repository;

import com.cpe.backend.Sportequipment.entity.Sportequipment;

import java.util.Collection;
import java.util.List;

import com.cpe.backend.Sportequipment.entity.Category;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
public interface SportequipmentRepository extends JpaRepository<Sportequipment, Long> {
    Sportequipment findById(long id);
    public List<Sportequipment> findByCategory(Category category);

   

    @Query(value ="SELECT * FROM Sportequipment s WHERE s.CATEGORY_ID= CATEGORY_ID", nativeQuery = true)
    Collection<Sportequipment> findCarByPlate(@Param("CATEGORY_ID") String CATEGORY_ID);
}
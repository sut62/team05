package com.cpe.backend.Sportequipment.repository;

import com.cpe.backend.Sportequipment.entity.Sporttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SporttypeRepository extends JpaRepository<Sporttype, Long> {
	Sporttype findById(long id);
}
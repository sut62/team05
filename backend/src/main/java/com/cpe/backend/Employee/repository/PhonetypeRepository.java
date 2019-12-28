package com.cpe.backend.Employee.repository;


import com.cpe.backend.Employee.entity.Phonetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PhonetypeRepository extends JpaRepository<Phonetype, Long> {
	Phonetype findById(long phonetype_id);
}

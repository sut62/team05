package com.cpe.backend.Members.repository;


import com.cpe.backend.Members.entity.Nametype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface NametypeRepository extends JpaRepository<Nametype, Long> {
	Nametype findById(long id);
}
package com.cpe.backend.Members.repository;


import com.cpe.backend.Members.entity.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ProvinceRepository extends JpaRepository<Province, Long> {
	Province findById(long id);
}
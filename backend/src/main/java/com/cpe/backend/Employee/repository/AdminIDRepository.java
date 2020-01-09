package com.cpe.backend.Employee.repository;


import com.cpe.backend.Employee.entity.AdminID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AdminIDRepository extends JpaRepository<AdminID, Long> {
	AdminID findById(long adminID_id);
}

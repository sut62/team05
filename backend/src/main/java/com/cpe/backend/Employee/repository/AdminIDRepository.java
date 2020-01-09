package com.cpe.backend.Employee.repository;

import java.util.Collection;

import com.cpe.backend.Employee.entity.AdminID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AdminIDRepository extends JpaRepository<AdminID, Long> {
	AdminID findById(long adminID_id);

	
	
	@Query( value = "SELECT * FROM AdminID z WHERE z.adminID  = :email and z.adminpass = :pass",
	nativeQuery = true)
	
   Collection<AdminID> findCheck(@Param("email") String email,@Param("pass") String pass);
}

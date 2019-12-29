package com.cpe.backend.Reservation.repository;

import java.util.Collection;

import com.cpe.backend.Reservation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
}
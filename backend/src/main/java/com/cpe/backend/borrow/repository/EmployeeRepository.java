package com.cpe.backend.borrow.repository;

import com.cpe.backend.borrow.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
}

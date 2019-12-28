package com.cpe.backend.Employee.repository;


import com.cpe.backend.Employee.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long emp_id);

}

//SELECT POSITION FROM JOBPOST,POSITION WHERE JOBPOST.POSITION_ID = POSITION.POSITION_ID

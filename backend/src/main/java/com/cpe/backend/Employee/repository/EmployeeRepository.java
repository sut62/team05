package com.cpe.backend.Employee.repository;

import java.util.Collection;

import com.cpe.backend.Employee.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long emp_id);
    @Query( value = "SELECT * FROM Employee z WHERE z.email = :email and z.password = :password",nativeQuery = true)
    Collection<Employee> findCheck(@Param("email") String email,@Param("password") String password);

}

//SELECT POSITION FROM JOBPOST,POSITION WHERE JOBPOST.POSITION_ID = POSITION.POSITION_ID

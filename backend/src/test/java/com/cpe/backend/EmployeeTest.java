package com.cpe.backend;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class EmployeeTest {
    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6008031_testEmployeeInsertFullDataOK() {
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee = employeeRepository.saveAndFlush(employee);
        
        Optional<Employee> found = employeeRepository.findById(employee.getEmp_id());
        assertEquals(employee.getEmployee(), found.get().getEmployee());
    }
    @Test
    void b6008031_testEmployeeNameMustNotBeNull() {
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName(null);
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
       
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Employee> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("name", v.getPropertyPath().toString());
    }
    @Test
    void b6008031_testEmployeeEmailMustHaveAddress() {
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }
    @Test
    void b6008031_testEmployeePhoneMustHaveNumber() {
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("X123456789");

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
    }
}
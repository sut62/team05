package com.cpe.backend;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Employee.entity.Phonetype;

import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.repository.PositionRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;

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

@DataJpaTest
public class EmployeeTest {
    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PhonetypeRepository phonetypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
//All
//   test การใส่ข้อมูลครบ

    @Test
    void b6008031_testEmployeeInsertFullDataOK() {
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
    
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        
        employee = employeeRepository.saveAndFlush(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getEmp_id());
        assertEquals(date, found.get().getTimeRegis());
        assertEquals("สมชาย นามสมมุติ", found.get().getName());
        assertEquals("somsom@gmail.com", found.get().getEmail());
        assertEquals("12345678", found.get().getPassword());
        assertEquals("0123456789", found.get().getPhonenumber());

        assertEquals(phonetype, found.get().getPhonetype());
        assertEquals(position, found.get().getPosition());
        assertEquals(province, found.get().getProvince());

    }
//@notnull
// Date
//   test การใส่ข้อมูล Date ต้องไม่เป็นค่าว่าง 
    
@Test
void b6008031_testEmployeeDateMustNotBeNull() {
    Position position = new Position();

    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);

    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
   
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(null);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);
   // result ต้องมี error 1 ค่าเท่านั้น
   assertEquals(1, result.size());
   // error message ตรงชนิด และถูก field
   ConstraintViolation<Employee> v = result.iterator().next();
   assertEquals("must not be null", v.getMessage());
   assertEquals("TimeRegis", v.getPropertyPath().toString());
}
// Name
//   test การใส่ข้อมูล Name ต้องไม่เป็นค่าว่าง 
    
    @Test
    void b6008031_testEmployeeNameMustNotBeNull() {
        Position position = new Position();

        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName(null);
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
       
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Employee> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("name", v.getPropertyPath().toString());
    }
//Email
//   test การใส่ข้อมูล Email ต้องไม่เป็นค่าว่าง 
    @Test
    void b6008031_testEmployeeEmailMustNotBeNull() {
    
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail(null);
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
          
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
       
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
 }

//Password
//   test การใส่ข้อมูล Password ต้องไม่เป็นค่าว่าง 
    @Test
    void b6008031_testEmployeePasswordMustNotBeNull() {
        

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
          
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword(null);
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
}
    
//Phonenumber
//   test การใส่ข้อมูล Phonenumber ต้องไม่เป็นค่าว่าง 
    @Test
    void b6008031_testEmployeePhonenumberMustNotBeNull() {
        
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
            
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber(null);
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field 
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
}
    
//Phonetype
//   test การใส่ข้อมูล Phonetype ต้องไม่เป็นค่าว่าง 
@Test
void b6008031_testEmployeePhonetypeMustNotBeNull() {
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);

    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
   
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(null);
    employee.setPosition(position);
    employee.setProvince(province);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);
   // result ต้องมี error 1 ค่าเท่านั้น
   assertEquals(1, result.size());
   // error message ตรงชนิด และถูก field
   ConstraintViolation<Employee> v = result.iterator().next();
   assertEquals("must not be null", v.getMessage());
   assertEquals("phonetype", v.getPropertyPath().toString());
}
//Position
//   test การใส่ข้อมูล Position ต้องไม่เป็นค่าว่าง 
@Test
void b6008031_testEmployeePositionMustNotBeNull() {
    Position position = new Position();

    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);

    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
   
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(null);
    employee.setProvince(province);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);
   // result ต้องมี error 1 ค่าเท่านั้น
   assertEquals(1, result.size());
   // error message ตรงชนิด และถูก field
   ConstraintViolation<Employee> v = result.iterator().next();
   assertEquals("must not be null", v.getMessage());
   assertEquals("position", v.getPropertyPath().toString());
}
//Province
//   test การใส่ข้อมูล Province ต้องไม่เป็นค่าว่าง 
@Test
void b6008031_testEmployeeProvinceMustNotBeNull() {
    Position position = new Position();

    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);

    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
   
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(null);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);
   // result ต้องมี error 1 ค่าเท่านั้น
   assertEquals(1, result.size());
   // error message ตรงชนิด และถูก field
   ConstraintViolation<Employee> v = result.iterator().next();
   assertEquals("must not be null", v.getMessage());
   assertEquals("province", v.getPropertyPath().toString());
}
//@email
//   test การใส่ข้อมูล Email ครงตามรูปแบบ
@Test
void b6008031_testEmployeeEmailMustHaveAddress() {

    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);

    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);

    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

    // error message ตรงชนิด และถูก field
    ConstraintViolation<Employee> v = result.iterator().next();
    assertEquals("must be a well-formed email address", v.getMessage());
    assertEquals("email", v.getPropertyPath().toString());
}
//@Pattern \\d{10}
//   test การใส่ข้อมูล Phonenumber ที่เป็นตัวเลข10ตัว 
    @Test
    void b6008031_testEmployeePhonenumberMustHaveNumber() {

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
      
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("X123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
    }
//   test การใส่ข้อมูล Phonenumber เกิน 10 ตัว
    @Test
    void b6008031_testEmployeePhonenumberNotBe11Digits() {
        
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("01234567890");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
    }
//   test การใส่ข้อมูล Phonenumber ไม่ถึง 10 ตัว
    @Test
    void b6008031_testEmployeePhonenumberNotBe9Digits() {
        
        Position position = new Position();

        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
    
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
    }
}
package com.cpe.backend;

import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;

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
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class MembersTest {
    private Validator validator;

    @Autowired
    private MembersRepository membersRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6007690_testInsertNameforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getUsername(), found.get().getUsername());
    }

    @Test
    void b6007690_testUsernameforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername(null);
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }
    
   
    @Test
    void b6007690_testtUsernameforMambersBeGreaterEqual5() {
        Members members = new Members();
        members.setUsername("Arm");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 15", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testtUsernameforMambersBeGreaterEqual15() {
        Members members = new Members();
        members.setUsername("Arm123456789101112");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 15", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }



@Test
    void b6007690_testUsernameNotpattern() {
        Members members = new Members();
        members.setUsername("213123w0^^^^^^");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        ConstraintViolation<Members> v = result.iterator().next();
        assertEquals("must match \"[A-Za-z0-9]{1,20}$\"", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }


    
    @Test
    void b6007690_testInsertnameforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getName(), found.get().getName());
    }

    
    @Test
    void b6007690_testNameforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName(null);
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }
    



    @Test
    void b6007690_testtNameforMambersBeGreaterEqual5() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิต");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 40", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testNameforMambersBeGreaterEqual15() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 40", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }

    

    @Test
    void b6007690_testInsertDateforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getDate(), found.get().getDate());
    }

    
    @Test
    void b6007690_testDateforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(null);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("date", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6007690_testInsertAddressforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getAddress(), found.get().getAddress());
    }

    @Test
    void b6007690_testAddressforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress(null);
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testAddressforMambersBeGreaterEqual5() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 80", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testAddressforMambersBeGreaterEqual15() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 80", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testInsertEmailforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getEmail(), found.get().getEmail());
    }

    @Test
    void b6007690_testEmailforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail(null);
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("email", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testEmailMustHaveAddress() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728");
        members.setPhonenumber("0902408126");

        Set<ConstraintViolation<Members>> result = validator.validate(members);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        
        assertEquals("must be a well-formed email address", result.iterator().next().getMessage());
        assertEquals("email", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testInsertPhonenumberforMembersOK() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members = membersRepository.saveAndFlush(members);
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals(members.getPhonenumber(), found.get().getPhonenumber());
    }

    @Test
    void b6007690_testPhonenumberforMembersMustNotBeNull() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber(null);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("phonenumber", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void B6007690_testCustomerUsernameMustBeUnique() {
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("kittiphun");
        java.sql.Date date = new java.sql.Date(2020-04-05);
        members.setDate(date);
        members.setAddress("ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm6827@gmail.com");
        members.setPhonenumber("0902408144");
        membersRepository.saveAndFlush(members);
        
    
        assertThrows(DataIntegrityViolationException.class, () -> {
            Members members2 = new Members();
        members2.setUsername("Arm6827");
        members2.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date2 = new java.sql.Date(2020-02-05);
        members2.setDate(date2);
        members2.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members2.setEmail("arm68276728@gmail.com");
        members2.setPhonenumber("0902408126");
        membersRepository.saveAndFlush(members2);
        });
    }


}
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
}
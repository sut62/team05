package com.cpe.backend;

import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;

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
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private NametypeRepository nametypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6007690_testInsertMembersOK() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);
        
        
        Optional<Members> found = membersRepository.findById(members.getMember_id());
        assertEquals("Arm6827", found.get().getUsername());
        assertEquals("กิตติพันธ์  เฟื่องคร", found.get().getName());
        assertEquals(date, found.get().getDate());
        assertEquals("195 ม.1 ต.แม่วงก์ อ.แม่วงก์", found.get().getAddress());
        assertEquals("arm68276728@gmail.com", found.get().getEmail());
        assertEquals("0902408126", found.get().getPhonenumber());
        assertEquals(gender, found.get().getGender());
        assertEquals(nametype, found.get().getNametype());
        assertEquals(province, found.get().getProvince());
    }

    @Test
    void b6007690_testUsernameforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Members members = new Members();
        members.setUsername(null);
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
       

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }
    
   
    @Test
    void b6007690_testtUsernameforMambersBeGreaterEqual5() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("Arm");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);


        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 15", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testtUsernameforMambersBeGreaterEqual15() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("Arm123456789101112");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 15", result.iterator().next().getMessage());
        assertEquals("username", result.iterator().next().getPropertyPath().toString());
    }



@Test
    void b6007690_testUsernameNotpattern() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("213123w0^^^^^^");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        ConstraintViolation<Members> v = result.iterator().next();
        assertEquals("must match \"[A-Za-z0-9]{1,20}$\"", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }


    
  

    
    @Test
    void b6007690_testNameforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName(null);
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }
    



    @Test
    void b6007690_testtNameforMambersBeGreaterEqual5() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิต");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 40", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testNameforMambersBeGreaterEqual15() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);    
       
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องครกิตติพันธ์ เพื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 40", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }

    

  

    
    @Test
    void b6007690_testDateforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(null);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("date", result.iterator().next().getPropertyPath().toString());
    }

  

    @Test
    void b6007690_testAddressforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress(null);
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testAddressforMambersBeGreaterEqual5() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);


        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 80", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6007690_testAddressforMambersBeGreaterEqual15() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 80", result.iterator().next().getMessage());
        assertEquals("address", result.iterator().next().getPropertyPath().toString());
    }


   

    @Test
    void b6007690_testEmailforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail(null);
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("email", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6007690_testEmailMustHaveAddress() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        
        assertEquals("must be a well-formed email address", result.iterator().next().getMessage());
        assertEquals("email", result.iterator().next().getPropertyPath().toString());
    }


    

    @Test
    void b6007690_testPhonenumberforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber(null);
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
    

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("phonenumber", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void B6007690_testCustomerUsernameMustBeUnique() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province); 

        
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("kittiphun");
        java.sql.Date date = new java.sql.Date(2020-04-05);
        members.setDate(date);
        members.setAddress("ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm6827@gmail.com");
        members.setPhonenumber("0902408144");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        membersRepository.saveAndFlush(members);
        
        
        assertThrows(DataIntegrityViolationException.class, () -> {
            Gender gender2 = new Gender();
        gender2.setGender("ชาย");
        gender2 = genderRepository.saveAndFlush(gender2);


        Nametype nametype2 = new Nametype();
        nametype2.setNametype("นาย");
        nametype2 = nametypeRepository.saveAndFlush(nametype2);

        Province province2 = new Province();
        province2.setProvince("นครสวรรค์");
        province2 = provinceRepository.saveAndFlush(province2); 

            Members members2 = new Members();
        members2.setUsername("Arm6827");
        members2.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date2 = new java.sql.Date(2020-02-05);
        members2.setDate(date2);
        members2.setAddress("ต.แม่วงก์ อ.แม่วงก์");
        members2.setEmail("arm68276728@gmail.com");
        members2.setPhonenumber("0902408126");
        members2.setGender(gender2);
        members2.setNametype(nametype2);
        members2.setProvince(province2);
        membersRepository.saveAndFlush(members2);
        });
    }

    @Test
    void b6007690_testGenderforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(null);
        members.setNametype(nametype);
        members.setProvince(province);
       

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("gender", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6007690_testNametypeforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(null);
        members.setProvince(province);
       

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("nametype", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6007690_testProvinceforMembersMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(null);
       

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("province", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6007690_testPhonenumberNotpattern() {
        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);


        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        
        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date = new java.sql.Date(2020-02-05);
        members.setDate(date);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("090240812006");
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);

        Set<ConstraintViolation<Members>> result = validator.validate(members);
        assertEquals(1, result.size());
        ConstraintViolation<Members> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("phonenumber", v.getPropertyPath().toString());
    }

}